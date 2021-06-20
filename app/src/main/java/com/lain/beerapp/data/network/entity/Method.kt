package com.lain.beerapp.data.network.entity

import com.squareup.moshi.Json

data class Method(
    val fermentation: Fermentation?,

    @Json(name = "mash_temp")
    val mashTemp: List<MashTemp>?,
    val twist: String?
)
