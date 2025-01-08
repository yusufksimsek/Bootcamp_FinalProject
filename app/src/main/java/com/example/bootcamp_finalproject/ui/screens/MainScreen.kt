import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.screens.components.CheckMoviesText
import com.example.bootcamp_finalproject.ui.screens.components.UpcomingMovies
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    cartViewModel: CartViewModel,
) {
    val moviesList = mainViewModel.moviesList.observeAsState(listOf())

    LaunchedEffect(key1 = true) {
        mainViewModel.loadMovies()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.black),
        contentPadding = PaddingValues(8.dp)
    ) {
        item { UpcomingMovies() }
        item { CheckMoviesText() }

        items(moviesList.value.chunked(2)) { moviePair ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Colors.black)
            ) {
                moviePair.forEach { movie ->
                    val url = "http://kasimadalan.pe.hu/movies/images/${movie.image}"
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .background(Colors.black)
                            .padding(4.dp),
                        shape = RoundedCornerShape(5.dp), // Kartın kenarlarını yuvarlıyoruz
                        colors = CardDefaults.cardColors(
                            containerColor = Colors.black // Kartın arka plan rengini siyah yapıyoruz
                        ),
                        elevation = CardDefaults.cardElevation(8.dp),
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .background(Colors.black)
                                .clickable {
                                    val movieJson = Gson().toJson(movie)
                                    navController.navigate("movieDetailScreen/${movieJson}")
                                }
                        ) {
                            GlideImage(
                                imageModel = url,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(140.dp, 170.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                            Text(
                                text = movie.name,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 8.dp, bottom = 6.dp),
                                fontSize = 17.sp,
                                color = Colors.white
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 18.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.movie_star),
                                    contentDescription = "Rating Star",
                                    tint = Colors.mainColor,
                                    modifier = Modifier.size(25.dp)
                                )

                                Spacer(modifier = Modifier.width(3.dp))

                                Text(
                                    text = movie.rating.toString(),
                                    color = Colors.movieItemColor,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.width(40.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Colors.mainColor)
                                        .padding(vertical = 6.dp, horizontal = 4.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    IconButton(
                                        onClick = {
                                            cartViewModel.addMovieCart(
                                                name = movie.name,
                                                image = movie.image,
                                                price = movie.price,
                                                category = movie.category,
                                                rating = movie.rating,
                                                year = movie.year,
                                                director = movie.director,
                                                description = movie.description,
                                                orderAmount = 1,
                                                userName = FirebaseAuth.getInstance().currentUser?.email.toString()
                                            )
                                        },
                                        modifier = Modifier
                                            .width(45.dp)
                                            .height(22.dp)
                                    ) {
                                        Text(
                                            text = "$${movie.price}",
                                            fontSize = 16.sp,
                                            color = Colors.black,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .offset(y = (-1).dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                // Eğer iki öğe yoksa boş alan bırak
                if (moviePair.size < 2) {
                    Box(modifier = Modifier.weight(1f)) {}
                }
            }
        }
    }
}