package com.lain.beerapp.data.dto

import java.io.Serializable
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass

/**
 * This class define a dto to use in the app for beer.
 * @author Ivan Martinez Jimenez.
 */
data class BeerDTO(

    /**
     * Id
     */
    val id: Int?,

    /**
     * Name
     */
    val name: String?,

    /**
     * Image
     */
    val image: String?,

    /**
     * Tag line
     */
    val tagLine: String?,

    /**
     * Description
     */
    val description: String?,

    /**
     * First Brewed Date
     */
    val firstBrewedDate: String?,

    /**
     * FoodPairing
     */
    val foodPairing: List<String>?

) : Serializable {
    /*==============================================================================================
    BUILDER PATTERN
    ==============================================================================================*/
    data class Builder(
        var id: Int? = null,
        var image: String? = null,
        var name: String? = null,
        var tagLine: String? = null,
        var description: String? = null,
        var firstBrewedDate: String? = null,
        var foodPairing: List<String>? = null
    ) {

        fun id(id: Int) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun image(image: String) = apply { this.image = image }
        fun tagLine(tagLine: String) = apply { this.tagLine = tagLine }
        fun description(description: String) = apply { this.description = description }
        fun firstBrewedDate(firstBrewedDate: String) =
            apply { this.firstBrewedDate = firstBrewedDate }

        fun foodPairing(foodPairing: List<String>) = apply { this.foodPairing = foodPairing }


        fun build() = BeerDTO(
            id = id,
            image = image,
            name = name,
            tagLine = tagLine,
            description = description,
            firstBrewedDate = firstBrewedDate,
            foodPairing = foodPairing
        )
    }

    /*==============================================================================================
    OVERRIDE METHODS
    ==============================================================================================*/
    override fun equals(other: Any?): Boolean {

        if (javaClass != other?.javaClass)
            return false

        other as BeerDTO

        if(id != other.id) return false
        if(image != other.image) return false
        if(name != other.name) return false
        if(tagLine != other.tagLine) return false
        if(description != other.description) return false
        if(firstBrewedDate != other.firstBrewedDate) return false

        return true
    }
}
