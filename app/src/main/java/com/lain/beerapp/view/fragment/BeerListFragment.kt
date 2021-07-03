package com.lain.beerapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lain.beerapp.R
import com.lain.beerapp.data.network.errors.HttpErrorResponse
import com.lain.beerapp.databinding.BeerListBinding
import com.lain.beerapp.utils.HandleErrors
import com.lain.beerapp.view.Router
import com.lain.beerapp.view.adapters.BeerAdapter
import com.lain.beerapp.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * This class contains the beer list.
 * @author Ivan Martinez Jimenez.
 */
@AndroidEntryPoint
class BeerListFragment : BaseFragment() {

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
     * Beer list binding
     */
    private var _binding: BeerListBinding? = null

    private val binding get() = _binding!!

    /**
     * Beer adapter
     */
    private val beerAdapter = BeerAdapter(beers = mutableListOf()) { beer ->
        Router.route(

            route = Router.Routes.BEER_LIST_TO_BEER_DETAIL,
            /**
             * Args
             */
            beer

        )

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = BeerListBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        binding.beerRV.apply {
            this.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            this.adapter = beerAdapter
        }

        beerViewModel.loading.observe(viewLifecycleOwner, {

            showLoader(loader = binding.loaderContainer.loader, loading = it)

        })

        /**
         * Observe errors.
         */
        beerViewModel.error.observe(viewLifecycleOwner, {

            binding.mainSRL.isRefreshing = false
            handleApiError(error = it, object : HandleErrors {
                override fun onHttpError(httpErrorResponse: HttpErrorResponse) {

                }

                override fun onNetworkError(throwable: Throwable) {

                }

                override fun unknownApiError(throwable: Throwable) {

                }

            })

        })

        /**
         * Observe beer list
         */
        beerViewModel.beers.observe(viewLifecycleOwner, {

            binding.mainSRL.isRefreshing = false
            beerAdapter.addBeers(beers = it)


        })

        /**
         * Request first beers
         */
        beerViewModel.findBeers(page = PAGE)

        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.app_name)

        return binding.root

    }

    override fun onStart() {
        super.onStart()
        binding.beerRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    PAGE++
                    beerViewModel.findBeers(page = PAGE)
                }
            }
        })

        binding.mainSRL.setOnRefreshListener {
            if (binding.mainSRL.isRefreshing) {
                PAGE = 1
                beerAdapter.clearBeers()
                beerViewModel.findBeers(page = PAGE)
            }
        }
    }

}