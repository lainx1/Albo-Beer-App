package com.lain.beerapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lain.beerapp.R
import com.lain.beerapp.data.network.entity.Beer
import com.lain.beerapp.databinding.BeerItemBinding
import com.lain.beerapp.utils.Utils


/**
 * This class contains the adapter for beer recyclerview.
 * @author Ivan Martinez Jimenez.
 */
class BeerAdapter(
    private val onClickBeer: (Beer) -> Unit
) : PagingDataAdapter<Beer, BeerAdapter.BeerViewHolder>(BEER_COMPARATOR) {

    /**
     * DiffUtil implementation
     */
    companion object{
        private val BEER_COMPARATOR = object : DiffUtil.ItemCallback<Beer>(){

            override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean = oldItem == newItem

        }
    }

    /**
     * View Holder
     */
    class BeerViewHolder(val beerItemBinding: BeerItemBinding) : RecyclerView.ViewHolder(beerItemBinding.root) {

        /**
         * Create ViewHolder
         */
        companion object {
            fun create(parent: ViewGroup): BeerViewHolder = BeerViewHolder(
                beerItemBinding = BeerItemBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false))
            )
        }

        /**
         * Bind data.
         * @param beer .
         * @param onClickBeer .
         */
        fun bind(beer: Beer, onClickBeer: (Beer) -> Unit) {

            beerItemBinding.beer = beer

            val parent = beerItemBinding.beerCard
            val beerIV = beerItemBinding.beerIV
            val context = beerItemBinding.root.context

            parent.setOnClickListener { onClickBeer(beer) }

            Glide.with(context)
                .load(beer.imageUrl)
                .placeholder(Utils.getCircularProgressDrawable(context = context))
                .into(beerIV)

        }

    }

    /*==============================================================================================
    ADAPTER METHODS
    ==============================================================================================*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder = BeerViewHolder.create(parent = parent)

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        val beer = getItem(position)
        if(beer != null)
            holder.bind(beer = beer, onClickBeer = onClickBeer)
    }

}