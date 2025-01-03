package com.example.bootcamp_finalproject.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.data.entity.Movies
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetailScreen(
    pullingMovie: Movies,
    cartViewModel : CartViewModel
    ) {

    val amount = remember { mutableStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Arka plan posteri
        BackGroundPoster(details = pullingMovie)

        // LazyColumn, içeriği arka planın üzerine yerleştiriyor
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ön plan posteri
            item {
                ForegroundPoster(details = pullingMovie)
            }

            // Film adı
            item {
                Text(
                    text = pullingMovie.name,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            // Film bilgileri (puan, yıl vs.)
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = Color.White)
                    Text(
                        text = pullingMovie.rating.toString(),
                        modifier = Modifier.padding(start = 6.dp),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.price_icon),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "${pullingMovie.price}$",
                        Modifier.padding(start = 1.dp),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = pullingMovie.year.toString(),
                        Modifier.padding(start = 6.dp),
                        color = Color.White
                    )
                }
            }

            // Özet başlığı ve içeriği
            item {
                Row(
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Summary",
                        tint = Color.White
                    )
                    Text(
                        text = "Summary",
                        Modifier.padding(start = 10.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Text(
                    text = pullingMovie.description,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    color = Color.White
                )
            }

            // Yönetmen başlığı ve içeriği
            item {
                Row(
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Director",
                        tint = Color.White
                    )
                    Text(
                        text = "Director",
                        Modifier.padding(start = 10.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Text(
                    text = pullingMovie.director,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    color = Color.White
                )
            }

            item{
                Column(modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "Amount: ${amount.value}",
                        fontSize = 20.sp,
                        color = Color.White
                        )

                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        TextButton(onClick = {
                            amount.value--
                        }) {
                            Text(text = "-")
                        }
                        Button(onClick = {
                            cartViewModel.addMovieCart(
                                name = pullingMovie.name,
                                image = pullingMovie.image,
                                price = pullingMovie.price,
                                category = pullingMovie.category,
                                rating = pullingMovie.rating,
                                year = pullingMovie.year,
                                director = pullingMovie.director,
                                description = pullingMovie.description,
                                orderAmount = amount.value,
                                userName = "yusuf_simsek"
                            )
                        }) {
                            Text(text = "Add to Cart")
                        }
                        TextButton(onClick = {
                            amount.value++
                        }) {
                            Text(text = "+")
                        }
                    }
                }
            }
        }
    }
}

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

@SuppressLint("Range")
@Composable
fun BackGroundPoster(details: Movies) {
    val url = "http://kasimadalan.pe.hu/movies/images/${details.image}"
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        GlideImage(
            imageModel = url,
            contentDescription = details.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .alpha(0.6f) // Arka planın şeffaflığını ayarlayabilirsiniz
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.8f)
                        )
                    )
                )
        )
    }
}