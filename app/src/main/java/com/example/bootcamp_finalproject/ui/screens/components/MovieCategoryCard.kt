package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieCategoryCard(movie: Movies) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(110.dp),
    ) {
        Column {
            val url = "http://kasimadalan.pe.hu/movies/images/${movie.image}"
            GlideImage(
                imageModel = url,
                contentDescription = null,
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}