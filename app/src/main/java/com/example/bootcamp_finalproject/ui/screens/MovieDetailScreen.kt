package com.example.bootcamp_finalproject.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.bootcamp_finalproject.data.entity.Movies

@Composable
fun MovieDetailScreen(pullingMovie: Movies) {
    Column {
        Text(text = pullingMovie.name)
    }
}