package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.R

@Composable
fun UpcomingMovies() {
    val moviePosters = listOf(
        R.drawable.jurassicworld,
        R.drawable.minecraft,
        R.drawable.captainamerica,
        R.drawable.karatekid,
        R.drawable._28years
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Upcoming Movies",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "2025",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(moviePosters) { poster ->
                UpcomingMovieItem(poster)
            }
        }
    }
}

