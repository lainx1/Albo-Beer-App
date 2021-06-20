package com.lain.beerapp.view.activity

import android.os.Bundle
import com.lain.beerapp.R
import com.lain.beerapp.view.Router
import kotlinx.android.synthetic.main.activity_error.*

/**
 * This class is the default error activity.
 */
class ErrorActivity : BaseActivity() {

    /*==============================================================================================
    ANDROID LIFE CYCLE METHODS METHODS
    ==============================================================================================*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)

        errorTV.text = intent.getStringExtra(Router.Extras.ERROR.name)
    }

    override fun onStart() {
        super.onStart()
        reconnectBTN.setOnClickListener {
            Router.goToMain(context =  this@ErrorActivity)
        }
    }
}