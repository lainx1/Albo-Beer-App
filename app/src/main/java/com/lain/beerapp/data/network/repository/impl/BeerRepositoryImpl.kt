package com.lain.beerapp.data.network.repository.impl

import arrow.core.Either
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.data.network.mapper.BeerMapper
import com.lain.beerapp.data.network.repository.BeerRepository
import com.lain.beerapp.data.network.source.PunkApi
import com.lain.beerapp.data.network.errors.ApiError
import java.util.stream.Collectors
import javax.inject.Inject

/**
 * This class contains the implementation for beer repository.
 * @author Ivan Martinez Jimenez.
 */
class BeerRepositoryImpl @Inject constructor(private val punkApi: PunkApi) : BeerRepository {
    override suspend fun findAll(page: Int): Either<ApiError, List<BeerDTO>> {

        punkApi.findAll(page = page).fold(
            ifLeft = {
                return Either.left(it)
            },
            ifRight = {
                return Either.right(it.stream().map { BeerMapper.map(beer = it) }
                    .collect(Collectors.toList()))
            }
        )
    }
}