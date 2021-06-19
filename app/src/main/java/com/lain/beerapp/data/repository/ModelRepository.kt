package com.lain.beerapp.data.repository

import androidx.annotation.WorkerThread
import com.lain.beerapp.data.dao.BeerDAO
import com.lain.beerapp.data.entity.Beer
import javax.inject.Inject


class ModelRepository @Inject constructor(private val beerDAO: BeerDAO) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun findAll(): List<Beer> = beerDAO.findAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(beer: Beer) : Long = beerDAO.insert(beer = beer)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(beer: Beer) = beerDAO.update(beer = beer)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(beer: Beer) = beerDAO.delete(beer = beer)
}