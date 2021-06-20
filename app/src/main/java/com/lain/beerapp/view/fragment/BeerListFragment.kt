package com.lain.beerapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lain.beerapp.R
import com.lain.beerapp.view.Router
import com.lain.beerapp.view.adapters.BeerAdapter
import com.lain.beerapp.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_beer_list.view.*
import kotlinx.android.synthetic.main.loader.*
import javax.inject.Inject

@AndroidEntryPoint
class BeerListFragment : BaseFragment() {

    var fragment: View? = null

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

        Router.goToBeerDetail(
            navController = requireActivity().findNavController(R.id.nav_host_fragment),
            beer = beer
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragment = inflater.inflate(R.layout.fragment_beer_list, container, false)

        // Inflate the layout for this fragment
        with(fragment!!.beerRV) {
            //this.setHasFixedSize(true)
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

        beerViewModel!!.beers.observe(viewLifecycleOwner, {

            fragment!!.mainSRL.isRefreshing = false
            beerAdapter.addBeers(beers = it)


        })

        beerViewModel.findBeers(page = PAGE)


        return fragment
    }


    override fun onStart() {
        super.onStart()
        fragment!!.beerRV.addOnScrollListener(object  : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    PAGE++
                    beerViewModel.findBeers(page = PAGE)
                }
            }
        })

        fragment!!.mainSRL.setOnRefreshListener {
            if (fragment!!.mainSRL.isRefreshing) {
                PAGE = 1
                beerAdapter.clearBeers()
                beerViewModel.findBeers(page = PAGE)
            }
        }
    }


}