package com.lain.beerapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A sample class model representation.
 * @param key: a key.
 */
@Entity(tableName = "models")
data class Model(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val key: Int)
