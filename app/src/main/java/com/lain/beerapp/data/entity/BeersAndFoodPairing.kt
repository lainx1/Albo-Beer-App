package com.lain.beerapp.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class BeersAndFoodPairing(
    @Embedded
    val beer: Beer,

    @Relation(
        parentColumn = "id",
        entityColumn = "beer_id",
        entity = FoodPairing::class
    )
    val foodPairing: List<FoodPairing>?

)