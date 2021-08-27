package com.lain.beerapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lain.beerapp.data.dao.BeerDAO
import com.lain.beerapp.data.dao.RemoteKeysDAO
import com.lain.beerapp.data.entity.BeerEntity
import com.lain.beerapp.data.entity.RemoteKeys

@Database(
    entities = [BeerEntity::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)
abstract class BeerDataBase : RoomDatabase(){

    abstract fun beerDao() : BeerDAO
    abstract fun remoteKeysDao(): RemoteKeysDAO

    companion object {

        @Volatile
        private var INSTANCE: BeerDataBase ?= null

        fun getInstance(context: Context): BeerDataBase = INSTANCE ?: synchronized(this){
            INSTANCE ?: buildDatabase(context = context)
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                BeerDataBase::class.java, "beer.db")
                .build()

    }

}