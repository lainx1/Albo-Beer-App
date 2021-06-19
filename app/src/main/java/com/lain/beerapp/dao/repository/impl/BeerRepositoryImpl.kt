package com.lain.beerapp.dao.repository.impl

import arrow.core.Either
import com.lain.beerapp.dao.entity.Beer
import com.lain.beerapp.dao.repository.BeerRepository
import com.lain.beerapp.network.PunkApi
import com.lain.beerapp.network.model.ApiError
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(private val punkApi: PunkApi) : BeerRepository {
    override suspend fun findAll(): Either<ApiError, List<Beer>> {
        return punkApi.findAll()
    }
}