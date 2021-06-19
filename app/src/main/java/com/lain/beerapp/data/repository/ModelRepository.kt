package com.lain.beerapp.data.repository

import androidx.annotation.WorkerThread
import com.lain.beerapp.data.dao.ModelDao
import com.lain.beerapp.model.Model
import javax.inject.Inject


class ModelRepository @Inject constructor(private val modelDao: ModelDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun findAll(): List<Model> = modelDao.findAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(model: Model) : Long = modelDao.insert(model = model)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(model: Model) = modelDao.update(model = model)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(model: Model) = modelDao.delete(model = model)
}