package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.example.bootcamp_finalproject.ui.theme.Colors

@Composable
fun SummaryText(pullingMovie: Movies,){
    Row(
        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Summary",
            Modifier
                .padding(start = 10.dp)
                .fillMaxWidth(),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Colors.moveDetailTitleColor
        )
    }
    Text(
        text = pullingMovie.description,
        modifier = Modifier.padding(horizontal = 10.dp),
        color = Colors.moveDetailTitleColor
    )
}
