package com.lain.beerapp.data.network.entity

/**
 * This class belongs to api response.
 * @author Ivan Martinez Jimenez.
 */
data class Ingredients(
    val hops: List<Hop>?,
    val malt: List<Malt>?,
    val yeast: String?
)
