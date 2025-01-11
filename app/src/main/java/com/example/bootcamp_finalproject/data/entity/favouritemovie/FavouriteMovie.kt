package com.example.bootcamp_finalproject.data.entity.favouritemovie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class FavouriteMovie(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val category: String,
    val rating: Double,
    val year: Int,
    val director: String,
    val description: String
) {
}