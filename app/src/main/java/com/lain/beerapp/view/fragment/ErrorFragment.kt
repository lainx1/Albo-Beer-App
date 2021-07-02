package com.lain.beerapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lain.beerapp.R
import com.lain.beerapp.data.dto.ErrorDTO
import com.lain.beerapp.databinding.ErrorBinding
import com.lain.beerapp.view.Router

/**
 * This class is the default error activity.
 * @author Ivan Martinez Jimenez.
 */
class ErrorFragment : Fragment() {

    /**s
     * Binding
     */
    private var _binding: ErrorBinding ?= null

    private val binding get() = _binding!!

    /**
     * Error
     */
    private var _error : ErrorDTO ?= null

    private val error get() = _error!!

    /*==============================================================================================
    ANDROID LIFE CYCLE METHODS METHODS
    ==============================================================================================*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ErrorBinding.inflate(layoutInflater)


        _error = arguments?.getSerializable(Router.Extras.ERROR.value) as ErrorDTO
        binding.error = error

        return binding.root
    }

    override fun onStart() {

        super.onStart()
        binding.reconnectBTN.setOnClickListener {

            Router.route(
                route = Router.Routes.ERROR_TO_BEER_LIST,
                requireContext()
            )

        }

    }
}