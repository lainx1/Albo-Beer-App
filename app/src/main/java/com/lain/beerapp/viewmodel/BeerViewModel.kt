package com.lain.beerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lain.beerapp.data.network.entity.Beer
import com.lain.beerapp.data.repository.BeerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * This class is a base view model, functional and ready to use.
 *
 * @param beerRepository: Example repository.
 */
class BeerViewModel @Inject constructor(
    private val beerRepository: BeerRepository
) : ViewModel() {


    private var beers : Flow<PagingData<Beer>> ?= null

    /**
     * Send a functional get request example.
     */
    fun findBeers() : Flow<PagingData<Beer>> {
        val lastResult = beers

        if(lastResult != null)
            return lastResult


        val newResult : Flow<PagingData<Beer>> = beerRepository.findAll().cachedIn(viewModelScope)

        beers = newResult

        return newResult
    }
}