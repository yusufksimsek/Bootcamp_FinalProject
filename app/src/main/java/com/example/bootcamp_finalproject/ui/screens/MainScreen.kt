package com.example.bootcamp_finalproject.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.screens.components.UpcomingMovies
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val moviesList = mainViewModel.moviesList.observeAsState(listOf())
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        mainViewModel.loadMovies()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        item { UpcomingMovies() }

        item {
            Text(
                text = "Check Movies",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(4.dp)
            )
        }

        items(
            count = moviesList.value.count(),
            itemContent = {
                val movie = moviesList.value[it]
                val url = "http://kasimadalan.pe.hu/movies/images/${movie.image}"
                Card(modifier = Modifier.padding(all = 4.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(start = 6.dp, top = 10.dp, end = 6.dp)
                            .fillMaxWidth()
                    ) {
                        GlideImage(
                            imageModel = url,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp,160.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )

                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp)
                        ) {
                            Text(text = movie.name, fontWeight = FontWeight.Bold)
                            Text(text = movie.category, fontWeight = FontWeight.SemiBold)
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color.Black)
                                    .padding(vertical = 6.dp, horizontal = 10.dp)
                            ) {
                                Text(text = movie.rating.toString(), color = Color.White)
                            }
                        }

                        Icon(
                            painter = painterResource(id = R.drawable.select_icon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
            }
        )
    }
}