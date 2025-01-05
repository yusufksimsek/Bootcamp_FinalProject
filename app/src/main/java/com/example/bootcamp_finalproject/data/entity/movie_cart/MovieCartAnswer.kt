package com.example.bootcamp_finalproject.data.entity.movie_cart

import com.example.bootcamp_finalproject.data.entity.movie_cart.Movie_Cart
import com.google.gson.annotations.SerializedName

data class MovieCartAnswer(
    @SerializedName("movie_cart")
    var movie_cart: List<Movie_Cart>?)
{
}