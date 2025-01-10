package com.example.bootcamp_finalproject.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.R
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
    val query by searchViewModel.searchQuery.observeAsState("")

    Column {
        SearchBar(
            query = query,
            onSearch = { newQuery ->
                searchViewModel.setSearchQuery(newQuery)
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(7.dp)
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
                            .height(150.dp),
                        shape = RoundedCornerShape(12.dp), // Kartın kenarlarını yuvarlıyoruz
                        colors = CardDefaults.cardColors(
                        containerColor = Colors.cartBackgroundColor // Kartın arka plan rengini siyah yapıyoruz
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
                                    .size(110.dp, 140.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop
                            )

                            // Text and Icon Section
                            Column(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .fillMaxHeight()
                                    .weight(1f),
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                // Name with Icon
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.movie),
                                        contentDescription = "Movie Icon",
                                        tint = Colors.searchCartTextColor,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = movie.name,
                                        fontWeight = FontWeight.Bold,
                                        color = Colors.mainColor,
                                        fontSize = 18.sp
                                    )
                                }

                                // Category with Icon
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.category),
                                        contentDescription = "Category Icon",
                                        tint = Colors.searchCartTextColor,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Category: ${movie.category}",
                                        color = Colors.searchCartTextColor,
                                        fontSize = 15.sp
                                    )
                                }

                                // Director with Icon
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.director),
                                        contentDescription = "Director Icon",
                                        tint = Colors.searchCartTextColor,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Director: ${movie.director}",
                                        color = Colors.searchCartTextColor,
                                        fontSize = 15.sp
                                    )
                                }

                                // Rating with Icon
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.movie_star),
                                        contentDescription = "Rating Icon",
                                        tint = Colors.mainColor,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Rating: ${movie.rating}",
                                        color = Colors.searchCartTextColor,
                                        fontSize = 15.sp
                                    )
                                }
                            }

                            // Arrow Icon Section
                            Icon(
                                painter = painterResource(id = R.drawable.arrow_right),
                                contentDescription = "Arrow Icon",
                                tint = Colors.barTitleColor,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            )
        }
    }
}