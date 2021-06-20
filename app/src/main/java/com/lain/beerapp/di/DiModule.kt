package com.lain.beerapp.di

import android.content.Context
import androidx.room.Room
import com.lain.beerapp.data.network.repository.BeerApiRepository
import com.lain.beerapp.data.network.repository.impl.BeerApiRepositoryImpl
import com.lain.beerapp.data.room.repository.ModelRepository
import com.lain.beerapp.data.network.converters.EitherCallAdapterFactory
import com.lain.beerapp.data.network.source.BASE_URL
import com.lain.beerapp.data.network.source.PunkApi
import com.lain.beerapp.data.repository.BeerRepository
import com.lain.beerapp.data.repository.impl.BeerRepositoryImpl
import com.lain.beerapp.data.room.dao.BeerDAO
import com.lain.beerapp.data.room.source.BeerDataBase
import com.lain.beerapp.viewmodel.BeerViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * This class contains the hilt dependency injection module.
 */
@Module
@InstallIn(ActivityComponent::class)
object DiModule {

    /**
     * Provides base api.
     * @return BaseApi: instance
     */
    @Provides
    fun provideBaseApi() : PunkApi{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder().add(
                    KotlinJsonAdapterFactory()
                ).build()
            )).addCallAdapterFactory(EitherCallAdapterFactory())
            .build()

        return retrofit.create(PunkApi::class.java)
    }

    /**
     * Provides beer api repository.
     * @param punkApi: punk api instance.
     * @return beer api repository instance
     */
    @Provides
    fun provideBeerApiRepository(punkApi: PunkApi) : BeerApiRepository = BeerApiRepositoryImpl(punkApi = punkApi)
    

    @Provides
    @Singleton
    fun provideBeerDatabase(@ApplicationContext appContext: Context) : BeerDataBase = Room.databaseBuilder(
        appContext,
        BeerDataBase::class.java,
        "beer_app_database"
    ).build()

    @Provides
    @Singleton
    fun provideBeerDao(beerDataBase: BeerDataBase) : BeerDAO = beerDataBase.beerDAO()

    /**
     * Provides local repository
     * @param beerDAO: model dao.
     * @return model repository.
     */
    @Provides
    fun provideBeerRoomRepository(beerDAO: BeerDAO) : ModelRepository = ModelRepository(beerDAO = beerDAO)

    /**
     * Provides beer repository.
     * @param beerApiRepository: beer api repository instance.
     * @return beer repository instance
     */
    @Provides
    fun provideBeerRepository(beerApiRepository: BeerApiRepository) : BeerRepository = BeerRepositoryImpl(beerApiRepository = beerApiRepository)

    /**
     * Provides beer view model.
     * @param beerRepository: beer repository instance.
     * @return BeerViewModel: instance
     */
    @Provides
    fun provideBeerViewModel(beerRepository: BeerRepository): BeerViewModel = BeerViewModel(beerRepository = beerRepository)

}