package com.lain.beerapp.network.mapper

import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.network.entity.Beer

/**
 * This class map beer to dto.
 * @author Ivan Martinez Jimenez.
 */
object BeerMapper {

    /**
     * MApp beer to dto.
     */
    fun map(beer: Beer) : BeerDTO{

        return BeerDTO
            .Builder()
            .id(beer.id!!)
            .name(beer.name!!)
            .image(beer.imageUrl ?: "")
            .tagLine(beer.tagline ?: "---")
            .description(beer.description ?: "---")
            .firstBrewedDate(beer.firstBrewed ?: "---")
            .foodPairing(beer.foodPairing ?: mutableListOf())
            .build()

    }

}