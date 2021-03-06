package com.lain.beerapp.view.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.lain.beerapp.data.network.errors.*
import com.lain.beerapp.utils.HandleErrors
import com.squareup.moshi.Moshi

/**
 * This class contains the common methods for fragments.
 * @author Ivan Martinez Jimenez.
 */
open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    /*==============================================================================================
    BASE METHODS
    ==============================================================================================*/
    /**
     * Show or hide a loader.
     * @param loader: the loader to hide / show.
     * @param loading: control the loader visibility, true show loading, false hide loading.
     */
    fun showLoader(loader: LottieAnimationView, loading: Boolean) {
        with(loader) {
            this.visibility = if (loading) View.VISIBLE else View.GONE
        }
    }

    /**
     * Handle the api errors.
     * @param error: the error to handle.
     * @param handleErrors: a interface to define the behavior for specific errors, if is null then send to error activity.
     */
    fun handleApiError(error: ApiError, handleErrors: HandleErrors) {
        when (error) {
            /**
             * Is [HttpError]
             */
            is HttpError -> {

                val httpErrorResponseAdapter =
                    Moshi.Builder().build().adapter(HttpErrorResponse::class.java)
                val httpErrorResponse = httpErrorResponseAdapter.fromJson(error.body)

                handleErrors.onHttpError(httpErrorResponse = httpErrorResponse!!)

            }

            /**
             * Is [NetworkError]
             */
            is NetworkError -> handleErrors.onNetworkError(throwable = error.throwable)

            /**
             * Is [UnknownApiError]
             */
            is UnknownApiError -> handleErrors.onNetworkError(throwable = error.throwable)

        }


    }


    /**
     * Set title to toolbar.
     * @param title the toolbar title.
     */
    fun setTitle(title : String){

        (requireActivity() as AppCompatActivity).supportActionBar?.title = title

    }


}