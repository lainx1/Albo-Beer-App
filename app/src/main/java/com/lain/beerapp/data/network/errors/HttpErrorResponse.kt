package com.lain.beerapp.data.network.errors

/**
 * This class is a http error representation.
* @author Ivan Martinez Jimenez.
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
