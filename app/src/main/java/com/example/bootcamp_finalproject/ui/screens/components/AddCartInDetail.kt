package com.example.bootcamp_finalproject.ui.screens.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AddCartInDetail(
    cartViewModel: CartViewModel,
    pullingMovie: Movies
) {
    val amount = remember { mutableStateOf(1) }
    val context = LocalContext.current
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
}