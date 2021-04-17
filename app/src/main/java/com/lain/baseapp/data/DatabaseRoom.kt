package com.lain.baseapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lain.baseapp.data.dao.ModelDao
import com.lain.baseapp.model.Model

@Database(entities = [Model::class], version = 1, exportSchema = true)
abstract class DatabaseRoom :RoomDatabase(){

        abstract fun modelDao(): ModelDao


//    companion object{
//
//        // Singleton prevents multiple instances of database opening at the
//        // same time.
//
//        @Volatile
//        private var INSTANCE : DatabaseRoom? = null
//
//        // if the INSTANCE is not null, then return it,
//        // if it is, then create the database
//        fun getDatabase(context: Context) : DatabaseRoom = INSTANCE ?: synchronized(this){
//
//            val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DatabaseRoom::class.java,
//                    "app_database"
//            ).build()
//
//            INSTANCE = instance
//
//            instance
//        }
//
//    }

}