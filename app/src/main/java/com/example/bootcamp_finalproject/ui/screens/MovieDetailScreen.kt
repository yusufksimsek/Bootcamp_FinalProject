package com.example.bootcamp_finalproject.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.bootcamp_finalproject.ui.screens.components.BackgroundPoster
import com.example.bootcamp_finalproject.ui.screens.components.ForegroundPoster
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.FavouriteViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetailScreen(
    pullingMovie: Movies,
    cartViewModel: CartViewModel,
    favouriteViewModel: FavouriteViewModel,
    mainViewModel: MainViewModel,
    navController: NavController
) {
    val amount = remember { mutableStateOf(1) }

    val context = LocalContext.current

    val similarMovies = remember {
        mainViewModel.getMoviesByCategory(pullingMovie.category).filter { it.id != pullingMovie.id }
    }

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
        BackgroundPoster(details = pullingMovie)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .zIndex(1f) // İkonları üst sıraya taşı
        ) {
            // Geri ikonunu sol üst köşeye yerleştir
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }

                // Favori ikonunu sağ tarafa yerleştir
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
                    }
                ) {
                    Icon(
                        imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Toggle Favorite",
                        tint = if (isFavourite) Color.Red else Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
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
                        Button(
                            onClick = {
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
                                    userName = FirebaseAuth.getInstance().currentUser?.email.toString(),
                                    onSuccess = {
                                        Toast.makeText(context, "Movie added to your cart successfully.", Toast.LENGTH_SHORT).show()
                                    },
                                )
                            },
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
                Spacer(modifier = Modifier.size(20.dp))
            }


            item {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Similar Movies",
                        modifier = Modifier.padding(10.dp),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Colors.moveDetailTitleColor
                    )

                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        items(similarMovies) { movie ->
                            MovieCard(movie)
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun MovieCard(movie: Movies) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(110.dp),
    ) {
        Column {
            val url = "http://kasimadalan.pe.hu/movies/images/${movie.image}"
            GlideImage(
                imageModel = url,
                contentDescription = null,
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
