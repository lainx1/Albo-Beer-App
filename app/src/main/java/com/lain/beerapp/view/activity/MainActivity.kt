package com.lain.beerapp.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.lain.beerapp.R
import com.lain.beerapp.view.`interface`.OnClickBeer
import com.lain.beerapp.view.adapters.BeerAdapter
import com.lain.beerapp.viewmodel.BeerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.loader.*
import javax.inject.Inject

/**
 * This class is the main activity.
 * @author Ivan Martinez Jimenez.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(){



    /*==============================================================================================
    ANDROID METHODS
    ==============================================================================================*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
    }


}