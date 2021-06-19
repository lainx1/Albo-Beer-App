package com.lain.beerapp.view.`interface`

import com.lain.beerapp.model.Model


/**
 * This interface define the click behavior for a list
 */
interface OnClickItem {

    /**
     * Retrieve the [model] that was clicked.
     * @param model: the model that was clicked.
     */
    fun onCLick(model: Model)
}