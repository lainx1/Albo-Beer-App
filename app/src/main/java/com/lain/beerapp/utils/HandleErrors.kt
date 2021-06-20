package com.lain.beerapp.utils

import com.lain.beerapp.data.source.DatabaseError
import com.lain.beerapp.network.errors.HttpErrorResponse

/**
 * This interface handle api response errors.
 */
interface HandleErrors {

    /**
     * Handle http error.
     * @param httpErrorResponse
     */
    fun onHttpError(httpErrorResponse: HttpErrorResponse)

    /**
     * Handle network error.
     * @param throwable: a kotlin error to handle.
     */
    fun onNetworkError(throwable: Throwable)


    /**
     * Handle unknown error.
     * @param throwable: a kotlin error to handle.
     */
    fun unknownApiError(throwable: Throwable)

    /**
     * Handle database error.
     * @param databaseError: a database error to handle.
     */
    fun onDatabaseError(databaseError: DatabaseError)
}