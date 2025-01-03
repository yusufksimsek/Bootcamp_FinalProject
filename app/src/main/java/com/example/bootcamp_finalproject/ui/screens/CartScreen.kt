package com.example.bootcamp_finalproject.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CartScreen(cartViewModel: CartViewModel) {
    val moviesList = cartViewModel.moviesList.observeAsState(listOf())

    LaunchedEffect(key1 = true) {
        cartViewModel.getMovieCart("yusuf_simsek")
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        val movies = moviesList.value ?: listOf()
        items(
            count = movies.count(),
            itemContent = {
                val movie = moviesList.value[it]
                Card(modifier = Modifier.padding(all = 5.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val url = "http://kasimadalan.pe.hu/movies/images/${movie.image}"
                        Box {
                            GlideImage(
                                imageModel = url,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(40.dp, 90.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                        }
                        Column(modifier = Modifier.padding(all = 10.dp)) {
                            Text(text = movie.orderAmount.toString())
                            Text(text = movie.name, fontSize = 20.sp)
                            Text(text = movie.year.toString())
                        }
                        IconButton(onClick = {
                            cartViewModel.deleteMovieCart(movie.cartId, "yusuf_simsek")
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.delete_icon),
                                contentDescription = "",
                                tint = Color.Gray
                            )
                        }
                    }
                }
            }
        )
    }
}
