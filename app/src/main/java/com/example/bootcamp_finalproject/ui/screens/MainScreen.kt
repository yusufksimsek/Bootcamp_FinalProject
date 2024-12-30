package com.example.bootcamp_finalproject.ui.screens


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.ui.screens.components.Movies
import com.example.bootcamp_finalproject.ui.screens.components.UpcomingMovies

@Composable
fun MainScreen() {
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

        items(10) { index ->
            Movies()
        }
    }
}