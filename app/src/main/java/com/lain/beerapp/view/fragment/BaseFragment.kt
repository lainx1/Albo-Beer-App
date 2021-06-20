package com.lain.beerapp.view.fragment

import android.view.View
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.lain.beerapp.network.model.*
import com.lain.beerapp.utils.errors.HandleErrors
import com.lain.beerapp.view.Router
import com.squareup.moshi.Moshi

open class BaseFragment : Fragment() {

    /*==============================================================================================
    BASE METHODS
    ==============================================================================================*/
    /**
     * Show or hide a loader.
     * @param loader: the loader to hide / show.
     * @param loading: control the loader visibility, true show loading, false hide loading.
     */
    fun showLoader(loader: LottieAnimationView, loading: Boolean){
        with(loader){
            this.visibility = if(loading) View.VISIBLE else View.GONE
        }
    }

    /**
     * Handle the api errors.
     * @param error: the error to handle.
     * @param handleErrors: a interface to define the behavior for specific errors, if is null then send to error activity.
     */
    fun handleApiError(error: ApiError, handleErrors: HandleErrors? = null){

        if(error is HttpError){

            val httpErrorResponseAdapter = Moshi.Builder().build().adapter(HttpErrorResponse::class.java)
            val httpErrorResponse = httpErrorResponseAdapter.fromJson(error.body)

            if(handleErrors != null){
                handleErrors.onHttpError(httpErrorResponse = httpErrorResponse!!)
                return
            }

            Router.goToError(context = requireContext(), error = "Error: ${httpErrorResponse?.status}: ${httpErrorResponse?.message}")


        }else if(error is NetworkError){

            if(handleErrors != null){
                handleErrors.onNetworkError(throwable = error.throwable)
                return
            }

            //This is the default behavior
            Router.goToError(context = requireContext(), error = "Error: ${error.throwable.message}")

        }else{

            error as UnknownApiError

            if(handleErrors != null){
                handleErrors.onNetworkError(throwable = error.throwable)
                return
            }


            Router.goToError(context = requireContext(), error = "Error: ${error.throwable.message}")

        }
    }

}