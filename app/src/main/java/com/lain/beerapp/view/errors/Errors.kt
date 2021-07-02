package com.lain.beerapp.view.errors

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.lain.beerapp.data.dto.ErrorDTO
import com.lain.beerapp.view.Router

/**
 * This class display error kinds.
 * @author Ivan Martinez Jimenez.
 */
object Errors {

    /**
     * Show toast error.
     * @param context .
     * @param message the message to show.
     */
    fun showToastError(context: Context, message: String) =  Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    /**
     * Show snack bar error.
     * @param view .
     * @param message the message to show.
     */
    fun showSnackBarError(view : View, message: String) = Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()

    /**
     * Show error route.
     * @param errorRoute route to error [Router.Routes].
     * @param errorDTO [ErrorDTO].
     */
    fun showErrorView(errorRoute: Router.Routes, errorDTO: ErrorDTO) = Router.route(errorRoute, errorDTO)

}