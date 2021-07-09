package com.lain.beerapp.data.network.repository

import com.lain.beerapp.data.network.entity.Beer

/**
 * This class contains the beer repository.
 * @author Ivan Martinez Jimenez.
 */
const val BEER_START_PAGE= 1;
const val NETWORK_PAGE_SIZE = 20
interface BeerApiRepository {

    suspend fun findAll(page : Int) : List<Beer>

}