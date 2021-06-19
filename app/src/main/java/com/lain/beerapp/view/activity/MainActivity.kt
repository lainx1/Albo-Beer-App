package com.lain.beerapp.view.activity

import android.os.Bundle
import com.lain.beerapp.R
import com.lain.beerapp.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.loader.*
import javax.inject.Inject

/**
 * This class is the main activity.
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    /**
     * The base view model.
     */
    @Inject
    lateinit var beerViewModel: BeerViewModel

    /*==============================================================================================
    ANDROID METHODS
    ==============================================================================================*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beerViewModel!!.loading.observe(this, {
            showLoader(loader = loader, loading =  it)
        })

        beerViewModel!!.error.observe(this, {
            handleApiError(error = it)
        })

        beerViewModel!!.beers.observe(this){
            val firstBeer = it.stream().findFirst().get()
            beerNameTV.text = firstBeer.name
        }

        beerViewModel.findBeers()
    }
}