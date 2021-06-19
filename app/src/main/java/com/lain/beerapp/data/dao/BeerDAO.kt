package com.lain.beerapp.data.dao

import androidx.room.*
import com.lain.beerapp.data.entity.Beer

@Dao
interface BeerDAO {

    @Query("SELECT * FROM  beers")
    suspend fun findAll() : List<Beer>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(beer: Beer) : Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(beer: Beer)

    @Delete
    suspend fun delete(beer: Beer)

}