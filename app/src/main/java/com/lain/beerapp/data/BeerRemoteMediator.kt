package com.lain.beerapp.data

import androidx.paging.*
import androidx.room.withTransaction
import com.lain.beerapp.data.database.BeerDataBase
import com.lain.beerapp.data.entity.BeerEntity
import com.lain.beerapp.data.entity.RemoteKeys
import com.lain.beerapp.data.network.entity.Beer
import com.lain.beerapp.data.network.errors.HttpError
import com.lain.beerapp.data.network.errors.HttpErrorResponse
import com.lain.beerapp.data.network.errors.NetworkError
import com.lain.beerapp.data.network.errors.UnknownApiError
import com.lain.beerapp.data.network.repository.BEER_START_PAGE
import com.lain.beerapp.data.network.repository.BeerApiRepository
import com.squareup.moshi.Moshi

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
    private val beerApiRepository: BeerApiRepository,
    private val beerDataBase: BeerDataBase
) : RemoteMediator<Int, Beer>() {

    override suspend fun initialize(): InitializeAction {
        // Launch remote refresh as soon as paging starts and do not trigger remote prepend or
        // append until refresh has succeeded. In cases where we don't mind showing out-of-date,
        // cached offline data, we can return SKIP_INITIAL_REFRESH instead to prevent paging
        // triggering remote refresh.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Beer>): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: BEER_START_PAGE
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                // If remoteKeys is null, that means the refresh result is not in the database yet.
                // We can return Success with `endOfPaginationReached = false` because Paging
                // will call this method again if RemoteKeys becomes non-null.
                // If remoteKeys is NOT NULL but its prevKey is null, that means we've reached
                // the end of pagination for prepend.
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                // If remoteKeys is null, that means the refresh result is not in the database yet.
                // We can return Success with endOfPaginationReached = false because Paging
                // will call this method again if RemoteKeys becomes non-null.
                // If remoteKeys is NOT NULL but its prevKey is null, that means we've reached
                // the end of pagination for append.
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }


        val response = beerApiRepository.findAll(page = page)
        val beers = mutableListOf<Beer>()

        var error : Throwable ?= null

        response.fold({

            when (it) {
                /**
                 * Is [HttpError]
                 */
                is HttpError -> {

                    val httpErrorResponseAdapter =
                        Moshi.Builder().build().adapter(HttpErrorResponse::class.java)
                    val httpErrorResponse = httpErrorResponseAdapter.fromJson(it.body)

                    error = Throwable(httpErrorResponse!!.message)

                }

                /**
                 * Is [NetworkError]
                 */
                is NetworkError -> error = it.throwable

                /**
                 * Is [UnknownApiError]
                 */
                is UnknownApiError -> error = it.throwable

            }

        }, { beers.addAll(it) })

        if(error != null)
            return MediatorResult.Error(error!!)

        val endOfPaginationReached = beers.isEmpty()

        beerDataBase.withTransaction {
            // clear all tables in the database

            if(loadType == LoadType.REFRESH){
                beerDataBase.remoteKeysDao().clearRemoteKeys()
                beerDataBase.beerDao().clearAll()
            }
            val prevKey = if (page == BEER_START_PAGE) null else page - 1
            val nextKey = if (endOfPaginationReached) null else page + 1
            val keys = beers.map {
                RemoteKeys(repoId = it.id!!.toLong(), prevKey = prevKey, nextKey = nextKey)
            }

            beerDataBase.remoteKeysDao().insertAll(keys)
            beerDataBase.beerDao().insertAll(beers.toList().map { BeerEntity(id = it.id!!, name = it.name!!, description = it.description!!, imageUrl = it.imageUrl!!) })

        }

        return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Beer>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { beer ->
                // Get the remote keys of the last item retrieved
                beerDataBase.remoteKeysDao().remoteKeysRepoId(beer.id!!.toLong())
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Beer>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { beer ->
                // Get the remote keys of the first items retrieved
                beerDataBase.remoteKeysDao().remoteKeysRepoId(beer.id!!.toLong())
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Beer>
    ): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { beerId ->
                beerDataBase.remoteKeysDao().remoteKeysRepoId(beerId.toLong())
            }
        }
    }

}