package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel

@Composable
fun SimilarMoviesList(
    details: Movies,
    mainViewModel: MainViewModel,
) {

    val similarMovies = remember {
        mainViewModel.getMoviesByCategory(details.category).filter { it.id != details.id }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Similar Movies",
            modifier = Modifier.padding(10.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Colors.moveDetailTitleColor
        )

        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(similarMovies) { movie ->
                MovieCategoryCard(movie)
            }
        }
    }
}