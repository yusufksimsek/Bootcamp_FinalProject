@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.data.entity.favourite_movie.FavouriteMovie
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.FavouriteViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetailScreen(
    pullingMovie: Movies,
    cartViewModel: CartViewModel,
    favouriteViewModel: FavouriteViewModel,
    navController: NavController
) {
    val amount = remember { mutableStateOf(1) }

    val favouriteMovieList by favouriteViewModel.favouriteMovies.observeAsState(emptyList())
    val isFavourite = remember(favouriteMovieList) {
        favouriteViewModel.isMovieFavourite(pullingMovie.id)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Arka plan posteri
        BackGroundPoster(details = pullingMovie)

        // Favori ve geri ikonlarını konumlandırma
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .zIndex(1f) // İkonları üst sıraya taşı
        ) {
            // Geri ikonunu sol üst köşeye yerleştir
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            // Favori ikonunu sağ üst köşeye yerleştir
            IconButton(
                onClick = {
                    val favouriteMovie = FavouriteMovie(
                        id = pullingMovie.id,
                        name = pullingMovie.name,
                        image = pullingMovie.image,
                        category = pullingMovie.category,
                        rating = pullingMovie.rating,
                        year = pullingMovie.year,
                        director = pullingMovie.director,
                        description = pullingMovie.description
                    )
                    favouriteViewModel.toggleFavourite(movie = favouriteMovie)
                },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Toggle Favorite",
                    tint = if (isFavourite) Color.Red else Color.White
                )
            }
        }

            // LazyColumn, içeriği arka planın üzerine yerleştiriyor
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Ön plan posteri
                item {
                    Spacer(modifier = Modifier.size(45.dp))
                    ForegroundPoster(details = pullingMovie)
                    Spacer(modifier = Modifier.size(45.dp))
                }

                // Film adı
                item {
                    Text(
                        text = pullingMovie.name,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Colors.moveDetailTitleColor,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(15.dp))
                }

                // Yönetmen ve Kategori bilgisi
                item {
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
                    Spacer(modifier = Modifier.size(5.dp))
                }

                // Film bilgileri (puan, yıl vs.)
                item {
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
                                    text = pullingMovie.rating.toString(),
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
                                    text = "${pullingMovie.price}$",
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
                                    text = pullingMovie.year.toString(),
                                    color = Colors.moveDetailTitleColor,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                }

                // Özet başlığı ve içeriği
                item {
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
                    Spacer(modifier = Modifier.size(20.dp))
                }

                // Miktar seçimi ve sepete ekleme
                item {
                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Amount: ${amount.value}",
                            fontSize = 16.sp,
                            color = Color.White
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(
                                onClick = {
                                if (amount.value > 1) amount.value--
                            },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Colors.mainColor
                                ),
                                modifier = Modifier.size(34.dp),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "-",
                                    color = Colors.black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
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
                                )},
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Colors.mainColor
                                ),
                                shape = RoundedCornerShape(3.dp)
                                ) {
                                Text(
                                    text = "Add to Cart",
                                    color = Colors.black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                            TextButton(
                                onClick = {
                                amount.value++
                            },
                                colors = ButtonDefaults.buttonColors(
                                containerColor = Colors.mainColor
                            ),
                                modifier = Modifier.size(34.dp),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "+",
                                    color = Colors.black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center
                                    )
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
                .alpha(0.5f) // Arka planın şeffaflığını ayarlayabilirsiniz
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