package com.example.bootcamp_finalproject.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.ui.screens.components.SearchBar
import com.example.bootcamp_finalproject.ui.viewmodels.SearchViewModel
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("DiscouragedApi")
@Composable
fun SearchScreen(searchViewModel: SearchViewModel) {

    val moviesList by searchViewModel.moviesList.observeAsState(emptyList())

    Column {
        SearchBar(
            onSearch = { query ->
                searchViewModel.searchMovies(query)
            }
        )
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            columns = GridCells.Fixed(count = 2)
        ) {
            items(
                count = moviesList.count(),
                itemContent = {
                    val movie = moviesList[it]
                    val url = "http://kasimadalan.pe.hu/movies/images/${movie.image}"
                    Card(
                        modifier = Modifier.padding(all = 3.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            GlideImage(
                                imageModel = url,
                                modifier = Modifier
                                    .size(120.dp, 180.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(text = movie.name)
                                Text(text = "${movie.price} $")
                            }
                        }
                    }
                }
            )
        }
    }
}