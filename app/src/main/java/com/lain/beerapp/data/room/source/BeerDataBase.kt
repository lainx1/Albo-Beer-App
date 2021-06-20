package com.lain.beerapp.data.room.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lain.beerapp.data.room.dao.BeerDAO
import com.lain.beerapp.data.room.entity.Beer
import com.lain.beerapp.data.room.entity.FoodPairing

/**
 * This class contains the implemetation to create a room database.
 * @author Ivan Martinez Jimenez.
 */
@Database(entities = [Beer::class, FoodPairing::class], version = 3, exportSchema = true)
abstract class BeerDataBase : RoomDatabase() {

    abstract fun beerDAO(): BeerDAO


    companion object {

        // Singleton prevents multiple instances of database opening at the
        // same time.

        @Volatile
        private var INSTANCE: BeerDataBase? = null

        // if the INSTANCE is not null, then return it,
        // if it is, then create the database
        fun getDatabase(context: Context): BeerDataBase = INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                BeerDataBase::class.java,
                "beer_app_database"
            ).build()

            INSTANCE = instance

            instance
        }

    }

}