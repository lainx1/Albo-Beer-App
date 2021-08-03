package com.lain.beerapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lain.beerapp.data.network.entity.Beer
import com.lain.beerapp.data.network.errors.HttpError
import com.lain.beerapp.data.network.errors.HttpErrorResponse
import com.lain.beerapp.data.network.errors.NetworkError
import com.lain.beerapp.data.network.errors.UnknownApiError
import com.lain.beerapp.data.network.repository.BEER_START_PAGE
import com.lain.beerapp.data.network.repository.BeerApiRepository
import com.lain.beerapp.data.network.repository.NETWORK_PAGE_SIZE
import com.squareup.moshi.Moshi

class BeerDataSource(private val beerApiRepository: BeerApiRepository) : PagingSource<Int, Beer>() {

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        return state.anchorPosition?.let { _anchorPosition ->
            state.closestPageToPosition(anchorPosition = _anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition = _anchorPosition)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {

        val position = params.key ?: BEER_START_PAGE

        val response = beerApiRepository.findAll(page = position)
        val beers = mutableListOf<Beer>()

        var error : LoadResult<Int, Beer> ?= null

        response.fold({

            when (it) {
                /**
                 * Is [HttpError]
                 */
                is HttpError -> {

                    val httpErrorResponseAdapter =
                        Moshi.Builder().build().adapter(HttpErrorResponse::class.java)
                    val httpErrorResponse = httpErrorResponseAdapter.fromJson(it.body)

                    error = LoadResult.Error(Throwable(httpErrorResponse!!.message))

                }

                /**
                 * Is [NetworkError]
                 */
                is NetworkError -> error = LoadResult.Error(it.throwable)

                /**
                 * Is [UnknownApiError]
                 */
                is UnknownApiError -> error = LoadResult.Error(it.throwable)

            }

        }, { beers.addAll(it) })

        if(error != null)
            return error as LoadResult<Int, Beer>

        val nextKey = if (beers.isEmpty()) {
            null
        } else {
            position + (params.loadSize / NETWORK_PAGE_SIZE)
        }

        return LoadResult.Page(
            data = beers,
            prevKey = if (position == BEER_START_PAGE) null else position - 1,
            nextKey = nextKey
        )
        
    }


}