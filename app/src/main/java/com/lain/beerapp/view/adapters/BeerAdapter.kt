package com.lain.beerapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lain.beerapp.R
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.databinding.BeerItemBinding
import com.lain.beerapp.utils.Utils


/**
 * This class contains the adapter for beer recyclerview.
 * @author Ivan Martinez Jimenez.
 */
class BeerAdapter(
    private val beers: MutableList<BeerDTO>,
    private val onClickBeer: (BeerDTO) -> Unit
) :
    RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {
    /**
     * View Holder
     */
    class BeerViewHolder(val beerItemBinding: BeerItemBinding) : RecyclerView.ViewHolder(beerItemBinding.root) {

        /**
         * Bind data.
         * @param beer .
         * @param onClickBeer .
         */
        fun bind(beer: BeerDTO, onClickBeer: (BeerDTO) -> Unit) {

            beerItemBinding.beer = beer

            beerItemBinding.beerCard.setOnClickListener { onClickBeer(beer) }

            Glide.with(beerItemBinding.root.context)
                .load(beer.image)
                .placeholder(Utils.getCircularProgressDrawable(context = beerItemBinding.root.context))
                .into(beerItemBinding.beerIV)

        }

    }

    /*==============================================================================================
    ADAPTER METHODS
    ==============================================================================================*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder =
        BeerViewHolder(
            beerItemBinding = BeerItemBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false))
        )

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(beer = beers[position], onClickBeer = onClickBeer)
    }


    override fun getItemCount(): Int = beers.size


    /*==============================================================================================
    PUBLIC METHODS
    ==============================================================================================*/
    /**
     * Add given beers to adapter.
     * @param beers beers to add.
     */
    fun addBeers(beers: List<BeerDTO>) {

        val start = this.beers.size
        this.beers.addAll(start, beers)
        notifyItemRangeInserted(start, beers.size)

    }

    /**
     * Clear all beers.
     */
    fun clearBeers() {
        this.beers.clear()
        notifyDataSetChanged()
    }

}