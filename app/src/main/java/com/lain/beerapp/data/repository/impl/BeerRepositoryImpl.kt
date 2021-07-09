package com.lain.beerapp.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import arrow.core.Either
import com.lain.beerapp.data.BeerDataSource
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.data.network.entity.Beer
import com.lain.beerapp.data.network.errors.ApiError
import com.lain.beerapp.data.network.mapper.BeerMapper
import com.lain.beerapp.data.network.repository.BeerApiRepository
import com.lain.beerapp.data.network.repository.NETWORK_PAGE_SIZE
import com.lain.beerapp.data.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import java.util.stream.Collectors
import javax.inject.Inject

/**
 * This class contains the beer repository implementation.
 * @author Ivan Martinez Jimenez.
 */
class BeerRepositoryImpl @Inject constructor(
    private val beerApiRepository: BeerApiRepository
) : BeerRepository{

    override fun findAll(): Flow<PagingData<Beer>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { BeerDataSource(beerApiRepository = beerApiRepository) }
        ).flow


    }

    override suspend fun save(beers: List<BeerDTO>) {
        TODO("Not yet implemented")
    }

}