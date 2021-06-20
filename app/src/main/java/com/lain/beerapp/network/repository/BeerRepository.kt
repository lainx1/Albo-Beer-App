package com.lain.beerapp.network.repository

import arrow.core.Either
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.network.model.ApiError

interface BeerRepository {

    suspend fun findAll(page : Int) : Either<ApiError, List<BeerDTO>>

}