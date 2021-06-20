package com.lain.beerapp.data.repository.impl

import arrow.core.Either
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.data.network.errors.ApiError
import com.lain.beerapp.data.network.mapper.BeerMapper
import com.lain.beerapp.data.network.repository.BeerApiRepository
import com.lain.beerapp.data.repository.BeerRepository
import java.util.stream.Collectors
import javax.inject.Inject

/**
 * This class contains the beer repository implementation.
 * @author Ivan Martinez Jimenez.
 */
class BeerRepositoryImpl @Inject constructor(
    private val beerApiRepository: BeerApiRepository
) : BeerRepository{
    override suspend fun findAll(page: Int): Either<ApiError, List<BeerDTO>> {

        beerApiRepository.findAll(page = page).fold({
            return Either.left(it)
        },{
            return Either.right(it.stream().map(BeerMapper::map).collect(Collectors.toList()))
        })

    }

    override suspend fun save(beers: List<BeerDTO>) {
        TODO("Not yet implemented")
    }

}