package com.lain.beerapp.view

import androidx.navigation.NavController
import com.lain.beerapp.R
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.data.dto.ErrorDTO
import com.lain.beerapp.view.fragment.BeerListFragmentDirections

/**
 * This object is the router to all activities in the app.
 * @author Ivan Martinez Jimenez.
 */
object Router{

    /**
     * Navigation controller.
     */
    lateinit var navController: NavController


    /**
     * The extras passed by activities.
     * If are some update, then also update nav_graph arguments name.
     */
    enum class Extras(val value: String) {
        ERROR("error"),
        BEER("beer")
    }

    /**
     * Routes available.
     */
    enum class Routes {
        BEER_LIST_TO_BEER_DETAIL,
        BEER_LIST_TO_ERROR,
        ERROR_TO_BEER_LIST
    }


    /*==============================================================================================
    ROUTER HANDLER
    ==============================================================================================*/
    /**
     * Routing handler.
     *
     * @param route [Routes].
     * @param arg required args for any route.
     *
     * Args:
     *
     * [Routes.BEER_LIST_TO_BEER_DETAIL] -> arg : [BeerDTO].
     * [Routes.BEER_LIST_TO_ERROR] -> arg : [ErrorDTO].
     * [Routes.ERROR_TO_BEER_LIST] -> (no arg)
     *
     */
    fun route(route: Routes, arg: Any ?= null){

        when(route){

            Routes.BEER_LIST_TO_BEER_DETAIL -> beerListToBeerDetail(navController = navController, beer = arg as BeerDTO)

            Routes.BEER_LIST_TO_ERROR -> beerListToError(navController = navController, error = arg as ErrorDTO)

            Routes.ERROR_TO_BEER_LIST -> errorToBeerList(navController = navController)

        }
    }

    /*==============================================================================================
    VIEW ROUTING
    ==============================================================================================*/


    /**
     * Go to beer detail.
     * @param navController [NavController].
     * @param beer [BeerDTO].
     */
    private fun beerListToBeerDetail(navController: NavController, beer: BeerDTO) = navController.navigate(
        BeerListFragmentDirections.actionBeerListFragmentToBeerDetailFragment(beer = beer)
    )



    /*==============================================================================================
    ERROR ROUTING
    ==============================================================================================*/

    /**
     * Go to error fragment.
     * @param navController android navigation controller.
     * @param error [ErrorDTO] to show.
     */
    private fun beerListToError(navController: NavController, error: ErrorDTO) = navController.navigate(
        BeerListFragmentDirections.actionBeerListFragmentToErrorFragment(error = error)
    )

    /*==============================================================================================
    ERROR TO VIEW
    ==============================================================================================*/

    /**
     * Go error to main
     * @param navController [NavController].
     */
    private fun errorToBeerList(navController: NavController) =
        navController.navigate(R.id.action_errorFragment_to_beerListFragment)

}