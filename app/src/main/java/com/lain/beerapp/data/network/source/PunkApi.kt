package com.lain.beerapp.data.network.source

import arrow.core.Either
import com.lain.beerapp.data.network.entity.Beer
import com.lain.beerapp.data.network.errors.ApiError
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This class is the base api, ready to use only changing the [BASE_URL], and defining new [ENDPOINT].
 */
const val BASE_URL = "https://api.punkapi.com/v2/"
const val ENDPOINT = "beers"
interface PunkApi{

    /**
     * A example of a get request.
     * @param number: any name.
     * @return Either: the api response.
     */
    @GET(value = ENDPOINT)
    suspend fun findAll(
        @Query(value = "page")page : Int,
        @Query(value = "per_page") perPage: Int? = 20
    ): Either<ApiError, List<Beer>>

}