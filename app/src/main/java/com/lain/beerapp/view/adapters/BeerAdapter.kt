package com.lain.beerapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lain.beerapp.R
import com.lain.beerapp.data.dto.BeerDTO
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
    class BeerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val beerCard = view.findViewById<CardView>(R.id.beerCard)
        private val beerIV = view.findViewById<ImageView>(R.id.beerIV)
        private val nameTV = view.findViewById<TextView>(R.id.nameTV)
        private val tagLineTV = view.findViewById<TextView>(R.id.tagLineTV)

        /**
         * Bind data.
         * @param beer .
         * @param onClickBeer .
         */
        fun bind(beer: BeerDTO, onClickBeer: (BeerDTO) -> Unit) {


            beerCard.setOnClickListener { onClickBeer(beer) }
            Glide.with(view.context)
                .load(beer.image)
                .placeholder(Utils.getCircularProgressDrawable(context = view.context))
                .into(beerIV)
            nameTV.text = beer.name
            tagLineTV.text = beer.tagLine

        }

    }

    /*==============================================================================================
    ADAPTER METHODS
    ==============================================================================================*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder =
        BeerViewHolder(
            view = LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false)
        )

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) =
        holder.bind(beer = beers[position], onClickBeer = onClickBeer)

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