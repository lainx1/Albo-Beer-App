package com.lain.beerapp.data.repository

import arrow.core.Either
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.data.network.errors.ApiError

/**
 * This class have the beer repository.
 * @author Ivan Martinez Jimenez.
 */
interface BeerRepository {

    /**
     * Find all beers.
     * @param page .
     */
    suspend fun findAll(page: Int): Either<ApiError, List<BeerDTO>>

    /**
     * Save beers.
     * @param beers to save.
     */
    suspend fun save(beers: List<BeerDTO>)

}