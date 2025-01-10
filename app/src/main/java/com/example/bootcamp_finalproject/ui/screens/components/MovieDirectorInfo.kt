package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.example.bootcamp_finalproject.ui.theme.Colors

@Composable
fun MovieDirectorInfo(pullingMovie: Movies) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Yönetmen Card
        Card(
            colors = CardDefaults.cardColors(containerColor = Colors.cartBackgroundColor),
            modifier = Modifier
                .padding(4.dp)
                .weight(1f), // Dengelemek için
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.director),
                    contentDescription = null,
                    tint = Colors.mainColor,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = pullingMovie.director,
                    color = Colors.moveDetailTitleColor
                )
            }
        }

        Spacer(modifier = Modifier.width(5.dp))

        // Kategori Card
        Card(
            colors = CardDefaults.cardColors(containerColor = Colors.cartBackgroundColor),
            modifier = Modifier
                .padding(4.dp)
                .width(100.dp)
                .weight(1f), // Dengelemek için
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.category),
                    contentDescription = null,
                    tint = Colors.mainColor,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = pullingMovie.category,
                    color = Colors.moveDetailTitleColor
                )
            }
        }
    }
}