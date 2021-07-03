package com.lain.beerapp.view.activity

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.lain.beerapp.R
import com.lain.beerapp.databinding.MainBinding
import com.lain.beerapp.view.Router
import dagger.hilt.android.AndroidEntryPoint

/**
 * This class is the main activity.
 * @author Ivan Martinez Jimenez.
 */
@AndroidEntryPoint
class MainActivity : BaseActivity(){

    /**
     * Binding
     */
    private var _binding : MainBinding?= null

    private val binding get() = _binding!!

    /*==============================================================================================
    ANDROID LIFE CYCLE METHODS METHODS
    ==============================================================================================*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = MainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        /**
         * Init nav controller
         */
        Router.navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onStart() {
        super.onStart()

        setupActionBarWithNavController(Router.navController)
    }

    override fun onSupportNavigateUp(): Boolean {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}