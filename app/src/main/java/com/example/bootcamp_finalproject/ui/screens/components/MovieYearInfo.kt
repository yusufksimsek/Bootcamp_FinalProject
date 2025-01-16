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
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.example.bootcamp_finalproject.ui.theme.Colors

@Composable
fun MovieYearInfo(details: Movies) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // İlk Item
        Card(
            colors = CardDefaults.cardColors(containerColor = Colors.cartBackgroundColor),
            modifier = Modifier
                .padding(4.dp)
                .width(100.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 7.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.movie_star),
                    contentDescription = null,
                    tint = Colors.mainColor,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = details.rating.toString(),
                    color = Colors.moveDetailTitleColor,
                    fontSize = 16.sp
                )
            }
        }

        // İkinci Item
        Card(
            colors = CardDefaults.cardColors(containerColor = Colors.cartBackgroundColor),
            modifier = Modifier
                .padding(4.dp)
                .width(100.dp),
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
                    painter = painterResource(id = R.drawable.price),
                    contentDescription = null,
                    tint = Colors.mainColor,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = "${details.price}$",
                    color = Colors.moveDetailTitleColor,
                    fontSize = 16.sp
                )
            }
        }

        // Üçüncü Item
        Card(
            colors = CardDefaults.cardColors(containerColor = Colors.cartBackgroundColor),
            modifier = Modifier
                .padding(4.dp)
                .width(100.dp),
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
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = null,
                    tint = Colors.mainColor,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = details.year.toString(),
                    color = Colors.moveDetailTitleColor,
                    fontSize = 16.sp
                )
            }
        }
    }
}