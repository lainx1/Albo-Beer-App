package com.lain.beerapp.di

import android.content.Context
import androidx.room.Room
import com.lain.beerapp.data.DatabaseRoom
import com.lain.beerapp.data.dao.ModelDao
import com.lain.beerapp.data.repository.ModelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DiLocalDBModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : DatabaseRoom = Room.databaseBuilder(
        appContext,
        DatabaseRoom::class.java,
        "app_database"
    ).build()

    @Provides
    @Singleton
    fun provideModelDao(databaseRoom: DatabaseRoom) : ModelDao = databaseRoom.modelDao()

    /**
     * Provides local repository
     * @param modelDao: model dao.
     * @return model repository.
     */
    @Provides
    fun provideModelRepository(modelDao: ModelDao) : ModelRepository = ModelRepository(modelDao = modelDao)
}