package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ForegroundPoster(details: Movies) {
    val url = "http://kasimadalan.pe.hu/movies/images/${details.image}"
    Box(
        modifier = Modifier
            .padding(top = 40.dp)
            .width(150.dp)
            .height(220.dp)
            .clip(RoundedCornerShape(6.dp)),
    ) {
        GlideImage(
            imageModel = url,
            contentDescription = details.name,
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
        )
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color(0xB91A1B1B)
                        )
                    ),
                    shape = RoundedCornerShape(6.dp)
                )
        )
    }
}