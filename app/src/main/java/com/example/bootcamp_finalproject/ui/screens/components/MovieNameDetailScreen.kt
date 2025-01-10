package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.example.bootcamp_finalproject.ui.theme.Colors

@Composable
fun MovieNameDetailScreen(pullingMovie: Movies) {
    Text(
        text = pullingMovie.name,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = Colors.moveDetailTitleColor,
        textAlign = TextAlign.Center
    )
}