package com.lain.beerapp.data.dto

/**
 * This class define a dto for beer.
 * @author Ivan Martinez Jimenez.
 */
data class BeerDTO(

    val id: Int?,

    val name: String?,

    val image: String?,

    val tagLine: String?,

    val description: String?,

    val firstBrewedDate: String?,

    val foodPairing: List<String>?

) {
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
        fun image(id: String) = apply { this.image = image }
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
}
