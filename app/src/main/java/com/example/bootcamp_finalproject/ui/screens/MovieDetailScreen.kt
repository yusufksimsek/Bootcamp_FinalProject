package com.example.bootcamp_finalproject.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.data.entity.favourite_movie.FavouriteMovie
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.example.bootcamp_finalproject.ui.screens.components.AddCartInDetail
import com.example.bootcamp_finalproject.ui.screens.components.AddFavInDetail
import com.example.bootcamp_finalproject.ui.screens.components.BackgroundPoster
import com.example.bootcamp_finalproject.ui.screens.components.ForegroundPoster
import com.example.bootcamp_finalproject.ui.screens.components.MovieCategoryCard
import com.example.bootcamp_finalproject.ui.screens.components.MovieDirectorInfo
import com.example.bootcamp_finalproject.ui.screens.components.MovieNameDetailScreen
import com.example.bootcamp_finalproject.ui.screens.components.MovieYearInfo
import com.example.bootcamp_finalproject.ui.screens.components.SimilarMoviesList
import com.example.bootcamp_finalproject.ui.screens.components.SummaryText
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.FavouriteViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.skydoves.landscapist.glide.GlideImage

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
