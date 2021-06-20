package com.lain.beerapp.data.room.entity

import androidx.room.*

/**
 * This class contains a food pairing entity to store in room.
 * @author Ivan Martinez Jimenez.
 */
@Entity(tableName = "food_pairings",
    foreignKeys = [ForeignKey(
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
        entity = Beer::class,
        parentColumns = ["id"],
        childColumns = ["beer_id"])]
)
data class FoodPairing(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "beer_id")
    val beerId: Long,

    @ColumnInfo(name = "food_pairing")
    val foodPairing: String,
)
