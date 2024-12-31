package com.example.bootcamp_finalproject.retrofit

import com.example.bootcamp_finalproject.data.entity.MoviesAnswer
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MoviesDao {
      //Base url -> http://kasimadalan.pe.hu/movies/getAllMovies.php
      // -> movies/getAllMovies.php
    @GET("movies/getAllMovies.php")
    suspend fun getAllMovies() : MoviesAnswer

}