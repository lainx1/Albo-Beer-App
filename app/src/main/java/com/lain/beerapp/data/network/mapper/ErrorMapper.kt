package com.lain.beerapp.data.network.mapper

import com.lain.beerapp.data.dto.ErrorDTO

/**
 * This class map beer to dto.
 * @author Ivan Martinez Jimenez.
 */
object ErrorMapper {

    /**
     * Map error to dto.
     */
    fun map(message: String) : ErrorDTO = ErrorDTO(
        message = message
    )

}