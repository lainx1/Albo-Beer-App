package com.lain.baseapp.network

import arrow.core.Either
import com.lain.baseapp.model.Model
import com.lain.baseapp.network.model.ApiError
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * This class is the base api, ready to use only changing the [BASE_URL], and defining new [ENDPOINT].
 */
const val BASE_URL = "https://mockend.com/lainx1/Android-Base-App/"
const val ENDPOINT = "posts/{number}"
interface BaseApi{

    /**
     * A example of a get request.
     * @param number: any name.
     * @return Either: the api response.
     */
    @GET(value = ENDPOINT)
    suspend fun requestGet(@Path("number") number: Int): Either<ApiError, Model>

}