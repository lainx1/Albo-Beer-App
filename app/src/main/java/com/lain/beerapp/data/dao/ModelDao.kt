package com.lain.beerapp.data.dao

import androidx.room.*
import com.lain.beerapp.model.Model

@Dao
interface ModelDao {

    @Query("SELECT * FROM  models")
    suspend fun findAll() : List<Model>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: Model) : Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(model: Model)

    @Delete
    suspend fun delete(model: Model)

}