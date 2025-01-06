package com.example.bootcamp_finalproject.ui.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.skydoves.landscapist.glide.GlideImage
@Composable
fun CartScreen(cartViewModel: CartViewModel) {
    val moviesList = cartViewModel.moviesList.observeAsState(listOf())

    LaunchedEffect(key1 = true) {
        cartViewModel.getMovieCart("yusuf_simsek")
    }

    if (moviesList.value.isNullOrEmpty()) {
        // Sepet boşsa gösterilecek uyarı mesajı
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Your cart is empty",
                fontSize = 20.sp,
                color = Color.Gray
            )
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            // LazyColumn: Kaydırılabilir ürün listesi
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp , top = 6.dp, bottom = 70.dp) // Footer için boşluk bırak
            ) {
                val movies = moviesList.value ?: listOf()
                items(
                    count = movies.count(),
                    itemContent = {
                        val movie = movies[it]
                        val url = "http://kasimadalan.pe.hu/movies/images/${movie.image}"
                        Card(
                            modifier = Modifier
                                .padding(all = 6.dp)
                                .height(150.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Colors.cartBackgroundColor
                            ),
                            elevation = CardDefaults.cardElevation(8.dp),
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                GlideImage(
                                    imageModel = url,
                                    modifier = Modifier
                                        .size(110.dp, 140.dp)
                                        .clip(RoundedCornerShape(10.dp)),
                                    contentScale = ContentScale.Crop
                                )

                                // Text and Icon Section
                                Column(
                                    modifier = Modifier
                                        .padding(start = 10.dp)
                                        .fillMaxHeight()
                                        .weight(1f),
                                    verticalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    // Name with Icon
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.movie),
                                            contentDescription = "Movie Icon",
                                            tint = Colors.movieItemColor,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = movie.name,
                                            fontWeight = FontWeight.Bold,
                                            color = Colors.mainColor,
                                            fontSize = 18.sp
                                        )
                                    }

                                    // Amount with Icon
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.amount),
                                            contentDescription = "Amount Icon",
                                            tint = Colors.movieItemColor,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Amount: ${movie.orderAmount}",
                                            color = Colors.movieItemColor,
                                            fontSize = 15.sp
                                        )
                                    }

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.price),
                                            contentDescription = "Price Icon",
                                            tint = Colors.movieItemColor,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Price: $${movie.orderAmount * movie.price}",
                                            color = Colors.movieItemColor,
                                            fontSize = 15.sp
                                        )
                                    }
                                }

                                // Delete Icon Section
                                IconButton(onClick = {
                                    cartViewModel.deleteMovieCart(movie.cartId, "yusuf_simsek")
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.delete_icon),
                                        contentDescription = "Delete Icon",
                                        tint = Colors.movieItemColor,
                                        modifier = Modifier.size(25.dp)
                                    )
                                }
                            }
                        }
                    }
                )
            }

            // Sabit Footer: Toplam fiyat ve Buy butonu
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .align(Alignment.BottomCenter)
                    .background(Colors.backgroundColor)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val totalPrice = moviesList.value.sumOf { it.orderAmount * it.price }

                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "Total",
                        fontSize = 16.sp,
                        color = Colors.barTitleColor
                    )
                    Text(
                        text = "$totalPrice $",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Colors.mainColor
                    )
                }

                Button(
                    onClick = { /* Buy işlemi */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Colors.mainColor
                    )
                ) {
                    Text(
                        text = "Buy",
                        fontSize = 16.sp,
                        color = Colors.black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
