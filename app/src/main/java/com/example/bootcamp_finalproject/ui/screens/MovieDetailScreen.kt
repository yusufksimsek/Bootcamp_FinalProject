package com.example.bootcamp_finalproject.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.example.bootcamp_finalproject.ui.screens.components.AddCartInDetail
import com.example.bootcamp_finalproject.ui.screens.components.AddFavInDetail
import com.example.bootcamp_finalproject.ui.screens.components.BackgroundPoster
import com.example.bootcamp_finalproject.ui.screens.components.ForegroundPoster
import com.example.bootcamp_finalproject.ui.screens.components.MovieDirectorInfo
import com.example.bootcamp_finalproject.ui.screens.components.MovieNameDetailScreen
import com.example.bootcamp_finalproject.ui.screens.components.MovieYearInfo
import com.example.bootcamp_finalproject.ui.screens.components.SimilarMoviesList
import com.example.bootcamp_finalproject.ui.screens.components.SummaryText
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.FavouriteViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel

@Composable
fun MovieDetailScreen(
    pullingMovie: Movies,
    cartViewModel: CartViewModel,
    favouriteViewModel: FavouriteViewModel,
    mainViewModel: MainViewModel,
    navController: NavController
) {

    AddFavInDetail(
        pullingMovie = pullingMovie,
        favouriteViewModel = favouriteViewModel,
        navController = navController)

        BackgroundPoster(details = pullingMovie)

        // LazyColumn, içeriği arka planın üzerine yerleştiriyor
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Foreground Poster
            item {
                Spacer(modifier = Modifier.size(45.dp))
                ForegroundPoster(details = pullingMovie)
                Spacer(modifier = Modifier.size(45.dp))
            }

            // MovieName
            item {
                MovieNameDetailScreen(pullingMovie = pullingMovie)
                Spacer(modifier = Modifier.size(15.dp))
            }

            // Director and Category
            item {
                MovieDirectorInfo(pullingMovie = pullingMovie)
                Spacer(modifier = Modifier.size(5.dp))
            }

            // Rating, price, year
            item {
                MovieYearInfo(pullingMovie = pullingMovie)
                Spacer(modifier = Modifier.size(20.dp))
            }

            // Summary
            item {
                SummaryText(pullingMovie = pullingMovie)
                Spacer(modifier = Modifier.size(20.dp))
            }

            // Amount and Add Cart
            item {
                AddCartInDetail(cartViewModel = cartViewModel, pullingMovie = pullingMovie)
                Spacer(modifier = Modifier.size(20.dp))
            }


            // Similar Movies List
            item {
                SimilarMoviesList(pullingMovie = pullingMovie, mainViewModel = mainViewModel)
            }
        }

    }
