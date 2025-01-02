package com.example.bootcamp_finalproject.retrofit

import com.example.bootcamp_finalproject.data.entity.CRUDAnswer
import com.example.bootcamp_finalproject.data.entity.MovieCartAnswer
import com.example.bootcamp_finalproject.data.entity.MoviesAnswer
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MoviesDao {
    //Base url -> http://kasimadalan.pe.hu/movies/getAllMovies.php
    // -> movies/getAllMovies.php
    @GET("movies/getAllMovies.php")
    suspend fun getAllMovies(): MoviesAnswer

    @POST("movies/insertMovie.php")
    @FormUrlEncoded
    suspend fun addCart(@Field("name") name: String,
                        @Field("image") image: String,
                        @Field("price") price: Int,
                        @Field("category") category: String,
                        @Field("rating") rating: Double,
                        @Field("year") year: Int,
                        @Field("director") director: String,
                        @Field("description") description: String,
                        @Field("orderAmount") orderAmount: Int,
                        @Field("userName") userName: String): CRUDAnswer

    @POST("movies/getMovieCart.php")
    @FormUrlEncoded
    suspend fun getMovieCart(@Field("userName") userName: String): MovieCartAnswer

    @POST("movies/deleteMovie.php")
    @FormUrlEncoded
    suspend fun deleteMovieCart(@Field("cartId") cartId:Int,
                       @Field("userName") userName:String
                       ) : CRUDAnswer

}