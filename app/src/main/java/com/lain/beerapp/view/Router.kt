package com.lain.beerapp.view

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.lain.beerapp.R
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.data.dto.ErrorDTO

/**
 * This object is the router to all activities in the app.
 * @author Ivan Martinez Jimenez.
 */
class Router{

    /**
     * The extras passed by activities.
     */
    enum class Extras(val value: String) {
        ERROR("error"),
        BEER("beer")
    }

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
     * @param args required args for any route.
     *
     * Args:
     *
     * [Routes.BEER_LIST_TO_BEER_DETAIL] -> 0 : [NavController], 1 : [BeerDTO].
     * [Routes.BEER_LIST_TO_ERROR] -> 0 : [NavController], 1 : [ErrorDTO].
     * [Routes.ERROR_TO_BEER_LIST] -> 0 : [NavController], 1 : [ErrorDTO].
     *
     */
    fun route(route: Routes, vararg args: Any){

        when(route){

            Routes.BEER_LIST_TO_BEER_DETAIL -> beerListToBeerDetail(navController = args[0] as NavController, beer = args[1] as BeerDTO)

            Routes.BEER_LIST_TO_ERROR -> beerListToError(navController = args[0] as NavController, error = args[1] as ErrorDTO)

            Routes.ERROR_TO_BEER_LIST -> errorToBeerList(navController = args[0] as NavController)

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
        R.id.action_beerListFragment_to_beerDetailFragment,
        bundleOf(Extras.BEER.value to beer)
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
        R.id.action_beerListFragment_to_errorFragment,
        bundleOf(Extras.ERROR.value to error)
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