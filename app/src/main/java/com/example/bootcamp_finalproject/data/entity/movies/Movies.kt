package com.example.bootcamp_finalproject.data.entity.movies

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("image")
    var image: String,

    @SerializedName("price")
    var price: Int,

    @SerializedName("category")
    var category: String,

    @SerializedName("rating")
    var rating: Double,

    @SerializedName("year")
    var year: Int,

    @SerializedName("director")
    var director: String,

    @SerializedName("description")
    var description: String
)