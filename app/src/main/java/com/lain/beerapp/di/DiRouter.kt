package com.lain.beerapp.di

import com.lain.beerapp.view.Router
import com.lain.beerapp.view.errors.Errors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * This class contains the hilt dependency injection module.
 */
@Module
@InstallIn(ActivityComponent::class)
object DiRouter {

    @Provides
    fun provideRouter() : Router = Router()

    @Provides
    fun provideErrors(router: Router) : Errors = Errors(router = router)

}