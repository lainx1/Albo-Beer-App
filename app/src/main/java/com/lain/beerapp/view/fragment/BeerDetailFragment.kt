package com.lain.beerapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lain.beerapp.R
import com.lain.beerapp.view.Router

class BeerDetailFragment : Fragment() {

    var fragment: View? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragment = inflater.inflate(R.layout.fragment_beer_detail, container, false)

        val beer = arguments?.getSerializable(Router.Extras.BEER.value)

        return fragment
    }

}