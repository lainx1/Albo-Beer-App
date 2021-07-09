package com.lain.beerapp.data.repository

import androidx.paging.PagingData
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.data.network.entity.Beer
import kotlinx.coroutines.flow.Flow

/**
 * This class have the beer repository.
 * @author Ivan Martinez Jimenez.
 */
interface BeerRepository {

    /**
     * Find all beers.
     */
    fun findAll(): Flow<PagingData<Beer>>

    /**
     * Save beers.
     * @param beers to save.
     */
    suspend fun save(beers: List<BeerDTO>)

}