package com.lain.beerapp.network.repository.impl

import arrow.core.Either
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.network.mapper.BeerMapper
import com.lain.beerapp.network.repository.BeerRepository
import com.lain.beerapp.network.source.PunkApi
import com.lain.beerapp.network.model.ApiError
import java.util.stream.Collectors
import javax.inject.Inject

class BeerRepositoryImpl @Inject constructor(private val punkApi: PunkApi) : BeerRepository {
    override suspend fun findAll(): Either<ApiError, List<BeerDTO>> {

        punkApi.findAll().fold(
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