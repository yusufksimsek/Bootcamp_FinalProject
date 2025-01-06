import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.ui.screens.components.CheckMoviesText
import com.example.bootcamp_finalproject.ui.screens.components.UpcomingMovies
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel
import com.google.gson.Gson
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel) {
    val moviesList = mainViewModel.moviesList.observeAsState(listOf())

    LaunchedEffect(key1 = true) {
        mainViewModel.loadMovies()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
                           .background(Colors.black),
        contentPadding = PaddingValues(8.dp)
    ) {
        item { UpcomingMovies() }
        item { CheckMoviesText() }

        items(moviesList.value.chunked(2)) { moviePair ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                moviePair.forEach { movie ->
                    val url = "http://kasimadalan.pe.hu/movies/images/${movie.image}"
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .clickable {
                                    val movieJson = Gson().toJson(movie)
                                    navController.navigate("movieDetailScreen/${movieJson}")
                                }
                        ) {
                            GlideImage(
                                imageModel = url,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(120.dp, 160.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )

                            Text(
                                text = movie.name,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Text(
                                text = movie.category,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color.Black)
                                    .padding(vertical = 6.dp, horizontal = 10.dp)
                            ) {
                                Text(text = movie.rating.toString(), color = Color.White)
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