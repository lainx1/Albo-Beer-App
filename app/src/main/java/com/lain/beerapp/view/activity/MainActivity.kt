package com.lain.beerapp.view.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lain.beerapp.R
import com.lain.beerapp.view.`interface`.OnClickBeer
import com.lain.beerapp.view.adapters.BeerAdapter
import com.lain.beerapp.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.loader.*
import javax.inject.Inject

/**
 * This class is the main activity.
 * @author Ivan Martinez Jimenez.
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    /**
     * The base view model.
     */
    @Inject
    lateinit var beerViewModel: BeerViewModel

    /**
     * Beer adapter
     */
    private val beerAdapter = BeerAdapter(beers = mutableListOf()) { beer -> {
        //TODO send to detail
    } }

    /*==============================================================================================
    ANDROID METHODS
    ==============================================================================================*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(beerRV){
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            this.adapter = beerAdapter
        }

        beerViewModel!!.loading.observe(this, {
            showLoader(loader = loader, loading =  it)
        })

        beerViewModel!!.error.observe(this, {
            mainSRL.isRefreshing = false
            handleApiError(error = it)
        })

        beerViewModel!!.beers.observe(this){
            mainSRL.isRefreshing = false
            beerAdapter.addBeers(beers = it)
        }

        beerViewModel.findBeers()
    }

    override fun onStart() {
        super.onStart()

        mainSRL.setOnRefreshListener {
            beerAdapter.clearBeers()
            beerViewModel.findBeers()
        }
    }
}