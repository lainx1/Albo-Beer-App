package com.lain.beerapp.data.repository.impl

import androidx.paging.*
import arrow.core.right
import com.lain.beerapp.data.BeerDataSource
import com.lain.beerapp.data.BeerRemoteMediator
import com.lain.beerapp.data.database.BeerDataBase
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.data.entity.BeerEntity
import com.lain.beerapp.data.network.entity.Beer
import com.lain.beerapp.data.network.repository.BeerApiRepository
import com.lain.beerapp.data.network.repository.NETWORK_PAGE_SIZE
import com.lain.beerapp.data.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * This class contains the beer repository implementation.
 * @author Ivan Martinez Jimenez.
 */
class BeerRepositoryImpl @Inject constructor(
    private val beerApiRepository: BeerApiRepository,
    private val beerDataBase: BeerDataBase
) : BeerRepository{

    override fun findAll(): Flow<PagingData<Beer>> {

        beerDataBase.beerDao().findAll()

        val pagingSourceFactory = {  beerDataBase.beerDao().findAll() }


        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = BeerRemoteMediator(
                beerApiRepository = beerApiRepository,
                beerDataBase = beerDataBase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow


    }

    override suspend fun save(beers: List<BeerDTO>) {
        TODO("Not yet implemented")
    }

    class BeerEntityDataSource(val beerEntities: List<BeerEntity>): PagingSource<Int, Beer>(){

        override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
            return 0
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> {
            return LoadResult.Page(
                data = beerEntities.map { Beer(
                    0.0,
                    0.0,
                    null,
                    null,
                    null,
                    it.description,
                    0.0,
                    null,
                    emptyList(),
                    0.0,
                    it.id,
                    it.imageUrl,
                    null,
                    null,
                    it.name,
                    0.0,
                    0.0,
                    null,
                    0,
                    0.0,
                    null,
                ) },
                prevKey = 0,
                nextKey = 0
            )
        }

    }

}