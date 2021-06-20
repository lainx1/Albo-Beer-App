package com.lain.beerapp.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This class contains a beer entity to store in room.
 * @author Ivan Martinez Jimenez.
 */
@Entity(tableName = "beers")
data class Beer(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "tag_line")
    val tagLine: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "first_brewed_date")
    val firstBrewedDate: String?
)
