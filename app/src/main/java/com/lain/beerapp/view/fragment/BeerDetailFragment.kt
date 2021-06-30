package com.lain.beerapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.lain.beerapp.R
import com.lain.beerapp.data.dto.BeerDTO
import com.lain.beerapp.databinding.BeerDetailBinding
import com.lain.beerapp.utils.Utils
import com.lain.beerapp.view.Router
import java.util.*


/**
 * This class is the Beer Detail.
 * @author Ivan Martinez Jiemenz.
 */
class BeerDetailFragment : Fragment() {

    /**
     * Binding
     */
    private var _binding : BeerDetailBinding ?= null

    private val binding get() = _binding!!

    /*==============================================================================================
    ANDROID LIFE CYCLE METHODS
    ==============================================================================================*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = BeerDetailBinding.inflate(inflater, container, false)


        val beer = arguments?.getSerializable(Router.Extras.BEER.value) as BeerDTO

        /**
         * Set title
         */
        (requireActivity() as AppCompatActivity).supportActionBar?.title = beer.name

        /**
         * Build food pairing
         */
        var foodPairing = ""
        for(_foodPairing in Optional.ofNullable(beer.foodPairing).orElse(listOf()))
            foodPairing += String.format(resources.getString(R.string.detail_activity_food_pairing_item), _foodPairing)

        /**
         * Load image
         */

        Glide.with(requireContext())
            .load(beer.image)
            .placeholder(Utils.getCircularProgressDrawable(context = requireContext()))
            .into(binding.beerIV)

        /**
         * Set data
         */
        binding.firstBrewedTV.text = String.format(resources.getString(R.string.detail_activity_first_brewed), beer.firstBrewedDate)
        binding.descriptionTV.text = String.format(resources.getString(R.string.detail_activity_description), beer.description)
        binding.foodPairingTV.text = foodPairing

        return binding.root

    }


}