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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.data.entity.Movies
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetailScreen(pullingMovie: Movies) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        BackGroundPoster(details = pullingMovie)
        ForegroundPoster(details = pullingMovie)
        Column(
            Modifier
                .padding(start = 20.dp, end = 20.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = pullingMovie.name,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 38.sp,
                color = Color.White,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "", tint = Color.White)
                Text(
                    text = pullingMovie.rating.toString(),
                    modifier = Modifier.padding(start = 6.dp),
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    painter = painterResource(id = R.drawable.price_icon),
                    contentDescription = "",
                    tint = Color.White
                )
                Text(
                    text = pullingMovie.year.toString(),
                    Modifier.padding(start = 6.dp),
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "",
                    tint = Color.White
                )

                Text(
                    text = pullingMovie.year.toString(),
                    Modifier.padding(start = 6.dp),
                    color = Color.White
                )
            }
            Row {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Person",
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
            Text(text = pullingMovie.description, color = Color.White)

            Row {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Person",
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
            Text(text = pullingMovie.director, color = Color.White)
        }
    }
}

@Composable
fun ForegroundPoster(details: Movies) {
    val url = "http://kasimadalan.pe.hu/movies/images/${details.image}"
    Box(
        modifier = Modifier
            .width(120.dp) // Genişliği artırarak denge sağladık
            .height(180.dp) // Yüksekliği genişlik ile orantılı yaptık
            .padding(top = 30.dp) // Üstten boşluğu ayarladık
            .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.TopCenter
    ) {
        GlideImage(
            imageModel = url,
            contentDescription = details.name,
            modifier = Modifier
                .width(120.dp)
                .height(180.dp) // Burada da aynı boyutları uyguluyoruz
                .clip(RoundedCornerShape(16.dp))
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color(0xB91A1B1B),
                        )
                    ), shape = RoundedCornerShape(16.dp)
                )
        )
    }
}

@Composable
fun BackGroundPoster(details: Movies) {
    val url = "http://kasimadalan.pe.hu/movies/images/${details.image}"
    Box(
        modifier = Modifier
            .fillMaxWidth() // Genişlik tam ekran
            .fillMaxHeight() // Yüksekliği sabit ama yeterince uzun
            .background(Color.Black)
    ) {
        GlideImage(
            imageModel = url,
            contentDescription = details.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp) // Yükseklik burada da aynı
                .alpha(0.6f) // Hafif transparan görünüm
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.8f) // Daha koyu bir alan ekledik
                        )
                    )
                )
        )
    }
}