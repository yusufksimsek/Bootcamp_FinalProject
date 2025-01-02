package com.example.bootcamp_finalproject.data.entity

import com.google.gson.annotations.SerializedName

data class MoviesAnswer(
    @SerializedName("movies")
    var movies: List<Movies>) {
}