package com.lain.beerapp.dao.repository

import arrow.core.Either
import com.lain.beerapp.dao.entity.Beer
import com.lain.beerapp.network.model.ApiError

interface BeerRepository {

    suspend fun findAll() : Either<ApiError, List<Beer>>

}