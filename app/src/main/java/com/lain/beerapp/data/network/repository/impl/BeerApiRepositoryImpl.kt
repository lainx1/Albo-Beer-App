package com.lain.beerapp.data.network.repository.impl

import arrow.core.Either
import com.lain.beerapp.data.network.entity.Beer
import com.lain.beerapp.data.network.errors.ApiError
import com.lain.beerapp.data.network.repository.BeerApiRepository
import com.lain.beerapp.data.network.source.PunkApi
import javax.inject.Inject

/**
 * This class contains the implementation for beer repository.
 * @author Ivan Martinez Jimenez.
 */
class BeerApiRepositoryImpl @Inject constructor(private val punkApi: PunkApi) : BeerApiRepository {

    override suspend fun findAll(page: Int): Either<ApiError, List<Beer>> =
        punkApi.findAll(page = page)

}