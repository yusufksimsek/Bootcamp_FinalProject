package com.example.bootcamp_finalproject.data.entity

data class Movie_Cart(
    var cartId:Int,
    var name:String,
    var image:String,
    var price:Int,
    var category:String,
    var rating:Double,
    var year:Int,
    var director:String,
    var description:String,
    var orderAmount:Int,
    var userName:String = "yusuf_simsek"
) {
}