package com.lain.beerapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.lain.beerapp.R
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.data.dto.ErrorDTO
import com.lain.beerapp.view.fragment.ErrorFragment
import com.lain.beerapp.view.activity.MainActivity

/**
 * This object is the router to all activities in the app.
 * @author Ivan Martinez Jimenez.
 */
object Router {

    /**
     * The extras passed by activities.
     */
    enum class Extras(val value: String) {
        ERROR("error"),
        BEER("beer")
    }

    /**
     * Go to main activity.
     * @param context: the current context.
     */
    fun goToMain(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        context.startActivity(intent)
    }

    /**
     * Go to error activity.
     * @param error: to show.
     */
    fun goToError(navController: NavController, error: ErrorDTO? = null) {


        val bundle = bundleOf(Extras.ERROR.value to error)
        navController.navigate(R.id.action_beerListFragment_to_errorFragment, bundle)

    }

    /**
     * Go error to main
     * @param navController [NavController].
     */
    fun errorToMain(navController: NavController){
        navController.navigate(R.id.action_errorFragment_to_beerListFragment)
    }

    /**
     * Go to beer detail.
     * @param navController [NavController].
     * @param beer [BeerDTO]
     */
    fun goToBeerDetail(navController: NavController, beer: BeerDTO) {
        val bundle = bundleOf(Extras.BEER.value to beer)
        navController.navigate(R.id.action_beerListFragment_to_beerDetailFragment, bundle)
    }

}