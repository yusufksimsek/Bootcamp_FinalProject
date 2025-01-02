package com.example.bootcamp_finalproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.data.entity.Movie_Cart
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@Composable
fun CartScreen(cartViewModel: CartViewModel) {

    val moviesList = cartViewModel.moviesList.observeAsState(listOf())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        val movies = moviesList.value ?: listOf()
        items(
            count = movies.count(),
            itemContent = {index ->
                val movie = movies[index]
                Card(modifier = Modifier.padding(all = 5.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val url = "http://kasimadalan.pe.hu/movies/images/${movie.image}"
                        Box {
                            GlideImage(
                                imageModel = url,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(40.dp, 90.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                        }

                        Column(modifier = Modifier.padding(all = 10.dp)) {
                            Text(text = movie.name, fontSize = 20.sp)
                            Text(text = movie.year.toString())
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(id = R.drawable.select_icon),
                                contentDescription = "",
                                tint = Color.Gray
                            )
                        }
                    }
                }
            }
        )
    }
}
