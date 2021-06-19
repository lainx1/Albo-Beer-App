package com.lain.beerapp.view.`interface`

import com.lain.beerapp.data.dto.BeerDTO


/**
 * This interface define the click behavior for a list
 */
interface OnClickBeer {

    /**
     * Retrieve the [beer] that was clicked.
     * @param beer: the model that was clicked.
     */
    fun onCLick(beer: BeerDTO)
}