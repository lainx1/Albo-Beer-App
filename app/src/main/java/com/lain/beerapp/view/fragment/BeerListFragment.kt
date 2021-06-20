package com.lain.beerapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.lain.beerapp.R
import com.lain.beerapp.data.source.DatabaseError
import com.lain.beerapp.network.errors.HttpErrorResponse
import com.lain.beerapp.utils.HandleErrors
import com.lain.beerapp.view.Router
import com.lain.beerapp.view.adapters.BeerAdapter
import com.lain.beerapp.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_beer_list.view.*
import kotlinx.android.synthetic.main.loader.*
import javax.inject.Inject

@AndroidEntryPoint
class BeerListFragment : BaseFragment() {

    /**
     * View
     */
    var fragment: View? = null

    /**
     * Pagination control
     */
    private var PAGE = 1

    /**
     * The base view model [BeerViewModel].
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

    /*==============================================================================================
    ANDROID LIFE CYCLE METHODS
    ==============================================================================================*/
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
            handleApiError(error = it, handleErrors = object : HandleErrors {
                override fun onHttpError(httpErrorResponse: HttpErrorResponse) {
                    Snackbar.make(requireContext(), fragment!!.parentLayout, httpErrorResponse.message, Snackbar.LENGTH_SHORT).show()
                }

                override fun onNetworkError(throwable: Throwable) {
                    Snackbar.make(requireContext(), fragment!!.parentLayout, "Se ha perdido la conection a internet", Snackbar.LENGTH_SHORT).show()
                }

                override fun unknownApiError(throwable: Throwable) {
                    Router.goToError(context = requireContext(), error = throwable.message)
                }

                override fun onDatabaseError(databaseError: DatabaseError) {

                }

            })

        })

        beerViewModel!!.beers.observe(viewLifecycleOwner, {

            fragment!!.mainSRL.isRefreshing = false
            beerAdapter.addBeers(beers = it)


        })

        beerViewModel.findBeers(page = PAGE)

        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)

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