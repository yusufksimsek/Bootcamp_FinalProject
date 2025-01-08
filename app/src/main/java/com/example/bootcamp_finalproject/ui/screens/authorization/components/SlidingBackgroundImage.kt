package com.example.bootcamp_finalproject.ui.screens.authorization.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bootcamp_finalproject.R

@Composable
fun SlidingBackgroundImage() {
    var positionX by remember { mutableStateOf(0f) }
    val infiniteTransition = rememberInfiniteTransition()

    val gradientColor = Brush.verticalGradient(
        listOf(
            Color(0x20000000),
            Color(0x80000000),
            Color(0xE6000000),
        )
    )

    val slidingAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -0.03f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 10000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    positionX = slidingAnimation * 1000

    Image(
        painter = painterResource(id = R.drawable.onboarding_background),
        contentDescription = "Background Image",
        modifier = Modifier
            .fillMaxSize()
            .scale(1.4f)
            .graphicsLayer {
                translationX = positionX.dp.toPx()
            },
        contentScale = ContentScale.Crop
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .background(brush = gradientColor))

}