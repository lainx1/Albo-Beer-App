package com.lain.beerapp.view

import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf
import com.lain.beerapp.view.activity.ErrorActivity
import com.lain.beerapp.view.activity.MainActivity
import androidx.navigation.NavController
import com.lain.beerapp.R
import com.lain.beerapp.data.dto.BeerDTO

/**
 * This object is the router to all activities in the app.
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
    fun goToMain(context: Context){
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        context.startActivity(intent)
    }

    /**
     * Go to error activity.
     * @param context: the current context.
     */
    fun goToError(context: Context, error: String? = null){
        val intent = Intent(context, ErrorActivity::class.java)
        intent.putExtra(Extras.ERROR.name, error ?: "")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        context.startActivity(intent)
    }

    /**
     * Go to beer detail.
     * @param navController [NavController].
     * @param beer [BeerDTO]
     */
    fun goToBeerDetail(navController: NavController, beer : BeerDTO){
        val bundle = bundleOf(Extras.BEER.value to beer)
        navController.navigate(R.id.action_beerListFragment_to_beerDetailFragment, bundle)
    }
}