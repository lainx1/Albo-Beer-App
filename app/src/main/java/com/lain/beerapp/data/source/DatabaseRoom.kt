package com.lain.beerapp.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lain.beerapp.data.dao.BeerDAO
import com.lain.beerapp.data.entity.Beer
import com.lain.beerapp.data.entity.FoodPairing

/**
 * This class contains the implemetation to create a room database.
 * @author Ivan Martinez Jimenez.
 */
@Database(entities = [Beer::class, FoodPairing::class], version = 3, exportSchema = true)
abstract class DatabaseRoom : RoomDatabase() {

    abstract fun beerDAO(): BeerDAO


    companion object {

        // Singleton prevents multiple instances of database opening at the
        // same time.

        @Volatile
        private var INSTANCE: DatabaseRoom? = null

        // if the INSTANCE is not null, then return it,
        // if it is, then create the database
        fun getDatabase(context: Context): DatabaseRoom = INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                DatabaseRoom::class.java,
                "beer_app_database"
            ).build()

            INSTANCE = instance

            instance
        }

    }

}