package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bootcamp_finalproject.R

@Composable
fun ComingMovies() {
    val moviePosters = listOf(
        R.drawable.jurassicworld,
        R.drawable.minecraft,
        R.drawable.captainamerica,
        R.drawable.karatekid,
        R.drawable._28years
    )

    LazyRow(
        contentPadding = PaddingValues(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(moviePosters) { poster ->
            MovieItem(poster)
        }
    }
}

@Composable
fun MovieItem(poster: Int) {
    Box {
        AsyncImage(
            model = poster,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(130.dp, 200.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }
}