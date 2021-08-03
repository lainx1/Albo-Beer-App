package com.lain.beerapp.view.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.lain.beerapp.R
import com.lain.beerapp.databinding.LoadStateFooterBinding

class BeerLoadStateViewHolder(
    private val binding: LoadStateFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState){
        if(loadState is LoadState.Error){
            binding.errorMsg.text =loadState.error.message
        }

        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error

    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): BeerLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.load_state_footer, parent, false)
            val binding = LoadStateFooterBinding.bind(view)
            return BeerLoadStateViewHolder(binding, retry)
        }
    }

}