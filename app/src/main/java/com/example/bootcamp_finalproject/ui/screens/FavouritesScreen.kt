package com.example.bootcamp_finalproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.bootcamp_finalproject.ui.viewmodels.FavouriteViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FavouritesScreen(favouriteViewModel: FavouriteViewModel) {
    val favouriteMoviesList by favouriteViewModel.favouriteMovies.observeAsState(emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
    ) {
        items(
            count = favouriteMoviesList.count(),
            itemContent = {
                val movie = favouriteMoviesList[it]
                val url = "http://kasimadalan.pe.hu/movies/images/${movie.image}"
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            GlideImage(
                                imageModel = url,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(180.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                            )
                            IconButton(
                                onClick = { favouriteViewModel.removeFavouriteMovie(movie) },
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(4.dp)
                                    .background(Color.Red.copy(alpha = 0.8f), shape = CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Remove from favourites",
                                    tint = Color.White
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = movie.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Rating: ${movie.rating}",
                            color = Color.Gray
                        )
                    }
                }
            }
        )
    }
}
