package com.lain.beerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.lain.beerapp.view.Router

/**
 * This class contains the splash screen activity.
 */
class Splash: AppCompatActivity() {

    /*==============================================================================================
    ANDROID METHODS
    ==============================================================================================*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        Router.goToMain(context = this)
        finish()
    }

}