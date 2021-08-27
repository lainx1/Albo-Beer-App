package com.lain.beerapp.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lain.beerapp.data.entity.BeerEntity
import com.lain.beerapp.data.network.entity.Beer

@Dao
interface BeerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(beerEntity: BeerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<BeerEntity>)

    @Query("SELECT * FROM  beers")
    fun findAll() : PagingSource<Int, BeerEntity>


    @Query("DELETE FROM beers")
    suspend fun clearAll()

}