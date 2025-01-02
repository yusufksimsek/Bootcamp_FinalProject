package com.example.bootcamp_finalproject.data.entity

import com.google.gson.annotations.SerializedName


data class Movies(
    @SerializedName("movies")
    var id:Int,
    var name:String,
    var image:String,
    var price:Int,
    var category:String,
    var rating:Double,
    var year:Int,
    var director:String,
    var description:String) {
}