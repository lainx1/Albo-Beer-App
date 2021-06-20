package com.lain.beerapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.lain.beerapp.R
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.utils.errors.Utils
import com.lain.beerapp.view.Router
import java.util.*

class BeerDetailFragment : Fragment() {

    var fragment: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragment = inflater.inflate(R.layout.fragment_beer_detail, container, false)


        val beer = arguments?.getSerializable(Router.Extras.BEER.value) as BeerDTO

        Glide.with(requireContext())
            .load(beer.image)
            .placeholder(Utils.getCircularProgressDrawable(context = requireContext()))
            .into(fragment!!.findViewById(R.id.beerIV))

        fragment!!.findViewById<TextView>(R.id.tagLineTV).text = beer.tagLine
        fragment!!.findViewById<TextView>(R.id.firstBrewedTV).text = "First brewed: ${beer.firstBrewedDate}"
        fragment!!.findViewById<TextView>(R.id.descriptionTV).text = "🍻 ${beer.description}"

        var foodPairing = ""
        for(_foodPairing in Optional.ofNullable(beer.foodPairing).orElse(listOf()))
            foodPairing += "😋 -> $_foodPairing\n"

        fragment!!.findViewById<TextView>(R.id.foodPairingTV).text = foodPairing


        (requireActivity() as AppCompatActivity).supportActionBar?.title = beer.name

        return fragment
    }


}