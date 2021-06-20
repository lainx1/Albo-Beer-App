package com.lain.beerapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.lain.beerapp.R
import com.lain.beerapp.view.adapters.BeerAdapter
import com.lain.beerapp.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_beer_list.view.*
import kotlinx.android.synthetic.main.loader.*
import javax.inject.Inject

@AndroidEntryPoint
class BeerListFragment : BaseFragment(), NestedScrollView.OnScrollChangeListener {

    var fragment : View? = null

    /**
     * Pagination control
     */
    private var PAGE = 1

    /**
     * The base view model.
     */
    @Inject
    lateinit var beerViewModel: BeerViewModel

    /**
     * Beer adapter
     */
    private val beerAdapter = BeerAdapter(beers = mutableListOf()) { beer ->
        {
            //TODO send to detail
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragment = inflater.inflate(R.layout.fragment_beer_list, container, false)

        // Inflate the layout for this fragment
        with(fragment!!.beerRV) {
            this.setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            this.adapter = beerAdapter
        }

        beerViewModel!!.loading.observe(viewLifecycleOwner, {
            showLoader(loader = loader, loading = it)
        })

        beerViewModel!!.error.observe(viewLifecycleOwner, {
            fragment!!.mainSRL.isRefreshing = false
            handleApiError(error = it)
        })

        beerViewModel!!.beers.observe(viewLifecycleOwner) {
            fragment!!.mainSRL.isRefreshing = false
            beerAdapter.addBeers(beers = it)
        }

        beerViewModel.findBeers(page = PAGE)

        return fragment
    }

    override fun onStart() {
        super.onStart()
        fragment!!.scrollView.setOnScrollChangeListener(this)

        fragment!!.mainSRL.setOnRefreshListener {
            if (fragment!!.mainSRL.isRefreshing) {
                PAGE = 1
                beerAdapter.clearBeers()
                beerViewModel.findBeers(page = PAGE)
            }
        }
    }

    /*==============================================================================================
    OnScrollChangeListener
    ==============================================================================================*/
    override fun onScrollChange(
        v: NestedScrollView?,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        if (scrollY == v!!.getChildAt(0).measuredHeight - v.measuredHeight) {
            PAGE++
            beerViewModel.findBeers(page = PAGE)
        }
    }

}