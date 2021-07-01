package com.lain.beerapp.data.dto

import java.io.Serializable

/**
 * This class define a error dto to use in error activity.
 * @author Ivan Martinez Jimenez.
 */
data class ErrorDTO (

    /**
     * Error message
     */
    var message : String ?= null,

) : Serializable