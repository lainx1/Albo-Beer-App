package com.lain.beerapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.lain.beerapp.R
import com.lain.beerapp.data.dto.BeerDTO


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

            val circularProgressDrawable = CircularProgressDrawable(view.context)
            with(circularProgressDrawable) {
                this.strokeWidth = 5f
                this.centerRadius = 30f
                this.start()
            }

            beerCard.setOnClickListener { onClickBeer(beer) }
            Glide.with(view.context).load(beer.image).placeholder(circularProgressDrawable)
                .centerCrop().into(beerIV)
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
        this.beers.addAll(beers)
        notifyDataSetChanged()
    }

    /**
     * Clear all beers.
     */
    fun clearBeers() {
        this.beers.clear()
        notifyDataSetChanged()
    }
}