package com.example.bootcamp_finalproject.data.entity.movies

import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.google.gson.annotations.SerializedName

data class MoviesAnswer(
    @SerializedName("movies")
    var movies: List<Movies>) {
}