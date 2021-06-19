package com.lain.beerapp.di

import com.lain.beerapp.network.repository.BeerRepository
import com.lain.beerapp.network.repository.impl.BeerRepositoryImpl
import com.lain.beerapp.data.repository.ModelRepository
import com.lain.beerapp.network.converters.EitherCallAdapterFactory
import com.lain.beerapp.network.source.BASE_URL
import com.lain.beerapp.network.source.PunkApi
import com.lain.beerapp.viewmodel.BeerViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
     * Provides beer repository.
     * @param punkApi: punk api instance.
     * @return BeerRepository: instance
     */
    @Provides
    fun provideBeerRepository(punkApi: PunkApi) : BeerRepository = BeerRepositoryImpl(punkApi = punkApi)

    /**
     * Provides beer view model.
     * @param beerRepository: beer repository instance.
     * @return BeerViewModel: instance
     */
    @Provides
    fun provideBeerViewModel(beerRepository: BeerRepository, modelRepository: ModelRepository): BeerViewModel = BeerViewModel(beerRepository = beerRepository, modelRepository = modelRepository)


}