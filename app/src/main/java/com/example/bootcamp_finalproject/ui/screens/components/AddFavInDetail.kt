package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.data.entity.favouritemovie.FavouriteMovie
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.example.bootcamp_finalproject.ui.viewmodels.FavouriteViewModel

@Composable
fun AddFavInDetail(
    details: Movies,
    favouriteViewModel: FavouriteViewModel,
    navController: NavController
) {

    val favouriteMovieList by favouriteViewModel.favouriteMovies.observeAsState(emptyList())
    val isFavourite = remember(favouriteMovieList) {
        favouriteViewModel.isMovieFavourite(details.id)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .zIndex(1f) // İkonları üst sıraya taşı
    ) {
        // Geri ikonunu sol üst köşeye yerleştir
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            // Favori ikonunu sağ tarafa yerleştir
            IconButton(
                onClick = {
                    val favouriteMovie = FavouriteMovie(
                        id = details.id,
                        name = details.name,
                        image = details.image,
                        category = details.category,
                        rating = details.rating,
                        year = details.year,
                        director = details.director,
                        description = details.description
                    )
                    favouriteViewModel.toggleFavourite(movie = favouriteMovie)
                }
            ) {
                Icon(
                    imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Toggle Favorite",
                    tint = if (isFavourite) Color.Red else Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}