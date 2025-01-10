package com.example.bootcamp_finalproject.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.google.firebase.auth.FirebaseAuth
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@Composable
fun CartScreen(cartViewModel: CartViewModel) {
    val moviesList = cartViewModel.moviesList.observeAsState(listOf())
    val listState = rememberLazyListState()
    val density = LocalDensity.current
    var previousScrollOffset by remember { mutableIntStateOf(0) }
    var isFooterVisible by remember { mutableStateOf(true) }

    val scope = rememberCoroutineScope()

    // SnackbarHostState to show messages
    val snackbarHostState = remember { SnackbarHostState() }

    // Update Visibility of Footer by following sliding list
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { currentOffset ->
                isFooterVisible = currentOffset <= previousScrollOffset
                previousScrollOffset = currentOffset
            }
    }

    LaunchedEffect(key1 = true) {
        cartViewModel.getMovieCart(FirebaseAuth.getInstance().currentUser?.email.toString())
    }

    if (moviesList.value.isNullOrEmpty()) {
        // Warning message if the Cart is empty
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
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp, top = 6.dp)
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
                                    cartViewModel.deleteMovieCart(
                                        movie.cartId,
                                        FirebaseAuth.getInstance().currentUser?.email.toString(),
                                        onSuccess = {
                                            // Show success Snackbar
                                            scope.launch {
                                                snackbarHostState.showSnackbar("Movie deleted successfully!")
                                            }
                                        },
                                        onFailure = {
                                            // Show failure Snackbar
                                            scope.launch {
                                                snackbarHostState.showSnackbar("Failed to delete movie. Please try again.")
                                            }
                                        }
                                    )
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

            // Footer
            AnimatedVisibility(
                visible = isFooterVisible,
                enter = slideInVertically { with(density) { 70.dp.roundToPx() } }
                        + expandVertically(expandFrom = Alignment.Bottom) + fadeIn(),
                exit = slideOutVertically { with(density) { 70.dp.roundToPx() } } + shrinkVertically() + fadeOut(),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
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

            // Snackbar Host at the bottom
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp), // Add padding to move it up a little
                contentAlignment = Alignment.BottomCenter
            ) {
                SnackbarHost(hostState = snackbarHostState)
            }
        }
    }
}
