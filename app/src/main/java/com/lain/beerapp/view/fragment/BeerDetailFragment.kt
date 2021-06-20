package com.lain.beerapp.view.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lain.beerapp.R
import com.lain.beerapp.view.Router

class BeerDetailFragment : Fragment() {

    var fragment: View? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragment = inflater.inflate(R.layout.fragment_beer_detail, container, false)

        val beer = arguments?.getSerializable(Router.Extras.BEER.value)

//        requireActivity().onBackPressedDispatcher.addCallback(
//            viewLifecycleOwner,
//            Router.onBackPressed(
//                navController = requireActivity().findNavController(R.id.nav_host_fragment)
//            )
//        )

        return fragment
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //requireActivity().onBackPressedDispatcher.
    }


}