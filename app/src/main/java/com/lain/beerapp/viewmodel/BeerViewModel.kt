package com.lain.beerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lain.beerapp.dao.entity.Beer
import com.lain.beerapp.dao.repository.BeerRepository
import com.lain.beerapp.data.repository.ModelRepository
import com.lain.beerapp.network.model.ApiError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * This class is a base view model, functional and ready to use.
 *
 * @param beerRepository: Example repository.
 */
class BeerViewModel @Inject constructor(
        private val beerRepository: BeerRepository,
        private val modelRepository: ModelRepository
        ): ViewModel(){

    /**
     * A live data error.
     */
    private val _error = MutableLiveData<ApiError>()

    /**
     * A live data for loading.
     */
    private val _loading = MutableLiveData<Boolean>()

    /**
     * The response live data.
     */
    private val _beers = MutableLiveData<List<Beer>>()

    /**
     * The entry to error.
     */
    val error: LiveData<ApiError> get() = _error

    /**
     * The entry to loading data.
     */
    val loading : LiveData<Boolean> get() = _loading

    /**
     * The entry to response.
     */
    val beers: LiveData<List<Beer>> get() = _beers

    /**
     * Send a functional get request example.
     */
    fun findBeers(){
        _loading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val either = beerRepository.findAll()

            withContext(Dispatchers.Main){
                _loading.value = false

                either.fold({
                    _error.value = it
                },{
                    _beers.value = it
                })
            }

        }
    }
}