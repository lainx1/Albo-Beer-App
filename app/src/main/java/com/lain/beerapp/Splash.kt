package com.lain.beerapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.lain.beerapp.view.activity.MainActivity

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

        startActivity(
            Intent(this, MainActivity::class.java).apply {
                this.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        )

        finish()
    }

}