package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UpcomingMovieItem(poster: Int) {
    Box {
        GlideImage(
            imageModel = poster,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp, 210.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }
}