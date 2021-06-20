package com.lain.beerapp.view.activity

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.lain.beerapp.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * This class is the main activity.
 * @author Ivan Martinez Jimenez.
 */
@AndroidEntryPoint
class MainActivity : BaseActivity(){



    /*==============================================================================================
    ANDROID LIFE CYCLE METHODS METHODS
    ==============================================================================================*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}