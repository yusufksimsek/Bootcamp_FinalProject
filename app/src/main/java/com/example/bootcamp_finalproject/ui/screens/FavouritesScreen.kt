package com.example.bootcamp_finalproject.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.FavouriteViewModel
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun FavouritesScreen(favouriteViewModel: FavouriteViewModel) {
    val favouriteMoviesList by favouriteViewModel.favouriteMovies.observeAsState(emptyList())

    if (favouriteMoviesList.isEmpty()) {
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
        LazyVerticalGrid(
            modifier = Modifier.padding(8.dp),
            columns = GridCells.Fixed(count = 2),
        ) {
            items(
                count = favouriteMoviesList.count(),
                itemContent = { index ->
                    val movie = favouriteMoviesList[index]
                    val url = "http://kasimadalan.pe.hu/movies/images/${movie.image}"

                    val swipeableState = rememberSwipeableState(0)
                    val offset = animateFloatAsState(
                        targetValue = swipeableState.offset.value,
                        animationSpec = tween(durationMillis = 400, easing = LinearEasing)
                    ).value

                    val isRemoved = swipeableState.offset.value > 50f // Silme durumu

                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isRemoved) Colors.favDeletionColor else Colors.black)
                            .swipeable(
                                state = swipeableState,
                                anchors = mapOf(0f to 0, 500f to 1), // 200f kaydırma mesafesi
                                orientation = Orientation.Horizontal,
                                thresholds = { _, _ -> FractionalThreshold(0.3f) }
                            )
                            .offset(x = offset.dp) // Animasyonlu kaydırma
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

                        // Kırmızı arka plan ve çöp kutusu simgesi ekleniyor
                        if (isRemoved) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center)
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.delete_icon), // Çöp kutusu simgesi
                                    contentDescription = "Trash icon",
                                    tint = Color.White,
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        }

                    }

                    // Kaydırma tamamlanınca silme işlemi
                    LaunchedEffect(swipeableState.offset.value) {
                        if (swipeableState.offset.value > 450f) { // 450f kaydırma eşiği
                            favouriteViewModel.removeFavouriteMovie(movie)
                        }
                    }
                }
            )
        }
    }
}
