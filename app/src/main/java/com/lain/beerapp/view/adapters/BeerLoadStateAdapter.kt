package com.lain.beerapp.view.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.lain.beerapp.view.viewholders.BeerLoadStateViewHolder

class BeerLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<BeerLoadStateViewHolder>(){

    override fun onBindViewHolder(holder: BeerLoadStateViewHolder, loadState: LoadState) = holder.bind(loadState = loadState)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): BeerLoadStateViewHolder = BeerLoadStateViewHolder.create(parent = parent, retry = retry)

}