package com.example.bootcamp_finalproject.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.ui.screens.components.SearchBar
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.SearchViewModel
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("DiscouragedApi")
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel
) {

    val moviesList by searchViewModel.moviesList.observeAsState(emptyList())

    Column {
        SearchBar(
            onSearch = { query ->
                searchViewModel.searchMovies(query)
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(Colors.black),
        ) {
            items(
                count = moviesList.count(),
                itemContent = {
                    val movie = moviesList[it]
                    val url = "http://kasimadalan.pe.hu/movies/images/${movie.image}"
                    Card(
                        modifier = Modifier
                            .padding(all = 6.dp)
                            .background(Colors.searchCartBackground)
                            .height(150.dp),
                        shape = RoundedCornerShape(12.dp), // Kartın kenarlarını yuvarlıyoruz
                        colors = CardDefaults.cardColors(
                        containerColor = Colors.searchCartBackground // Kartın arka plan rengini siyah yapıyoruz
                        ),
                        elevation = CardDefaults.cardElevation(8.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp)
                                .clickable {
                                    val movieJson = Gson().toJson(movie)
                                    navController.navigate("movieDetailScreen/${movieJson}")
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            GlideImage(
                                imageModel = url,
                                modifier = Modifier
                                    .size(90.dp, 130.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop
                            )

                            // Text Section
                            Column(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .fillMaxHeight()
                                    .weight(1f),
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = movie.name,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                )
                                Text(
                                    text = "Category: ${movie.category}",
                                    color = Color.White,
                                )
                                Text(
                                    text = "Director: ${movie.director}",
                                    color = Color.White,
                                )
                                Text(
                                    text = "Rating: ${movie.rating}",
                                    color = Color.White,
                                )
                            }

                            // Arrow Icon Section
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Arrow Icon",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            )
        }
    }
}