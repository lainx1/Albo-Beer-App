package com.lain.beerapp.network

import arrow.core.Either
import com.lain.beerapp.dao.entity.Beer
import com.lain.beerapp.network.model.ApiError
import retrofit2.http.GET

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
    suspend fun findAll(): Either<ApiError, List<Beer>>

}