package com.lain.beerapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.lain.beerapp.R
import com.lain.beerapp.data.network.entity.Beer
import com.lain.beerapp.databinding.BeerListBinding
import com.lain.beerapp.view.adapters.BeerAdapter
import com.lain.beerapp.view.adapters.BeerLoadStateAdapter
import com.lain.beerapp.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This class contains the beer list.
 * @author Ivan Martinez Jimenez.
 */
@AndroidEntryPoint
class BeerListFragment : BaseFragment() {

    /**
     * Search job
     */
    private var findAllJob: Job? = null

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
    private val beerAdapter = BeerAdapter { beer: Beer -> }

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

        binding.beerRV.adapter= beerAdapter.withLoadStateHeaderAndFooter(
            header = BeerLoadStateAdapter { beerAdapter.retry() },
            footer = BeerLoadStateAdapter { beerAdapter.retry() }
        )


        /**
         * Request first beers
         */
        beerViewModel.findBeers()

        /**
         * Set title
         */
        val title = getString(R.string.app_name)
        setTitle(title = title)

        findAllJob()
        initFindAll()



        return binding.root

    }

    private fun findAllJob() {

        findAllJob?.cancel()
        findAllJob = lifecycleScope.launch {
            beerViewModel.findBeers().collectLatest { beerAdapter.submitData(it) }
        }

    }

    private fun initFindAll() {

        lifecycleScope.launch {
            beerAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.beerRV.scrollToPosition(0) }
        }

    }

}