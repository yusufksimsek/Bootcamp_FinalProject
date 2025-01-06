package com.example.bootcamp_finalproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.FavouriteViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FavouritesScreen(favouriteViewModel: FavouriteViewModel) {
    val favouriteMoviesList by favouriteViewModel.favouriteMovies.observeAsState(emptyList())

    if (favouriteMoviesList.isEmpty()) {
        // Favori listesi boşsa bir mesaj göster
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Your favourite list is empty",
                fontSize = 20.sp,
                color = Color.Gray
            )
        }
    } else {
        // Favori listesi doluysa filmleri göster
        LazyVerticalGrid(
            modifier = Modifier.padding(8.dp),
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
                            .background(Colors.black)
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
                                        .padding(4.dp),
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.delete_icon),
                                        contentDescription = "Remove from favourites",
                                        tint = Color.Red
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = movie.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = Colors.movieItemColor,
                                fontWeight = FontWeight.Bold
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.star_icon),
                                    contentDescription = "Rating star",
                                    tint = Colors.mainColor,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = movie.rating.toString(),
                                    color = Colors.movieItemColor
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}
