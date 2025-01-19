package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.skydoves.landscapist.glide.GlideImage
import kotlin.math.absoluteValue

@Composable
fun UpcomingMovies() {
    val moviePosters = listOf(
        R.drawable.jurassicworld,
        R.drawable.minecraft,
        R.drawable.captainamerica,
        R.drawable.karatekid,
        R.drawable._28years
    )

    val movies = moviePosters.mapIndexed { index, poster ->
        Item(id = index, posterRes = poster)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, bottom = 8.dp, top = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Upcoming Movies",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f),
                color = Colors.white
            )
            Text(
                text = "2025",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Colors.mainColor
            )
        }

        SnapAlignedLazyRow(
            modifier = Modifier.fillMaxWidth(),
            items = movies
        )
    }
}

@Composable
fun SnapAlignedLazyRow(modifier: Modifier = Modifier, items: List<Item>) {
    val itemWidth = 200.dp
    val lazyListState = rememberLazyListState()

    // Dp'yi Px'e dönüştürmek için LocalDensity kullanıyoruz
    val itemWidthPx = with(LocalDensity.current) { itemWidth.toPx() }

    LazyRow(
        modifier = modifier,
        state = lazyListState,
        contentPadding = PaddingValues(horizontal = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(items.size) { index ->
            // offset hesaplamasını derivedStateOf ile yapıyoruz
            val offset = remember(lazyListState) {
                derivedStateOf {
                    val visibleItems = lazyListState.layoutInfo.visibleItemsInfo
                    val currentItem = visibleItems.find { it.index == index }
                    currentItem?.let {
                        val itemCenter = it.offset + (itemWidthPx / 2)  // itemWidthPx kullanıyoruz
                        val viewportCenter = lazyListState.layoutInfo.viewportEndOffset / 2f
                        val distance = (viewportCenter - itemCenter).absoluteValue
                        (distance / viewportCenter).coerceIn(0f, 1f)
                    } ?: 1f
                }
            }.value  // Offset değerine erişim burada yapılır

            val scale = 0.8f + (1f - offset.coerceIn(0f, 1f)) * 0.2f

            Card(
                modifier = Modifier
                    .width(itemWidth)
                    .padding(8.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        alpha = scale
                    },
                colors = CardDefaults.cardColors(containerColor = Color.Black),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.elevatedCardElevation(4.dp)
            ) {
                Column {
                    GlideImage(
                        imageModel = items[index].posterRes,
                        contentDescription = "Card Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(240.dp)
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

data class Item(val id: Int, val posterRes: Int)