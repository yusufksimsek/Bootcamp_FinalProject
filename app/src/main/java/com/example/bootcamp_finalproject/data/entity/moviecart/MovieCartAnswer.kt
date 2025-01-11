package com.example.bootcamp_finalproject.data.entity.moviecart

import com.google.gson.annotations.SerializedName

data class MovieCartAnswer(
    @SerializedName("movie_cart")
    var movie_cart: List<MovieCart>?)
{
}