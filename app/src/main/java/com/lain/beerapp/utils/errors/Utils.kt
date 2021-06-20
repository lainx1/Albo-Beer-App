package com.lain.beerapp.utils.errors

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

object Utils {
    fun getCircularProgressDrawable(context: Context) : CircularProgressDrawable{
        val circularProgressDrawable = CircularProgressDrawable(context)
        with(circularProgressDrawable) {
            this.strokeWidth = 5f
            this.centerRadius = 30f
            this.start()
        }
        return circularProgressDrawable
    }
}