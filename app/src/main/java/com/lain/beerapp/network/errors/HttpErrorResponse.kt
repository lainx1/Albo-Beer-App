package com.lain.beerapp.network.errors

/**
 * This class is a http error representation.
 */
data class HttpErrorResponse(
        /**
         * A code status.
         */
        val status: Int,

        /**
         * the type of error.
         */
        val type: String,

        /**
         * The message of error.
         */
        val message: String
)
