package com.blackfox.podlodka

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blackfox.podlodka.data.AppData
import com.blackfox.podlodka.layout.ActionButton
import com.blackfox.podlodka.layout.RatingBar
import com.blackfox.podlodka.layout.Tag
import com.blackfox.podlodka.ui.theme.AppIcons
import com.blackfox.podlodka.ui.theme.fonts
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import java.util.*
import kotlin.math.max
import kotlin.math.min

@Composable
fun DotaHomeworkCompose(appData: AppData) {
    val scrollState = rememberLazyListState()

    Box {
        Content(appData, scrollState)
        AppBar(appData, scrollState)
    }
}


@Composable
fun Rating(modifier: Modifier = Modifier, rating: AppData.Rating) {
    Column(modifier = modifier) {
        Text(
            text = "Review & Ratings",
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            letterSpacing = 0.6.sp,
            color = Color(0xFFEEF2FB),
            fontWeight = FontWeight.Bold,
            fontFamily = fonts
        )
        Row(modifier = Modifier
            .padding(top = 12.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = rating.value.toString(),
                textAlign = TextAlign.Start,
                fontSize = 48.sp,
                letterSpacing = 0.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = fonts
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                RatingBar(rating = rating.value)
                Text(
                    text = "70M Reviews",
                    modifier = Modifier.padding(top = 6.dp),
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    letterSpacing = 0.5.sp,
                    color = Color(0xFFA8ADB7),
                    fontFamily = fonts
                )
            }

        }

    }

}

val AppBarCollapsedHeight = 56.dp
val AppBarExpendedHeight = 363.dp
val ImageExpanded = 296.dp

@Composable
fun AppBar(appData: AppData, scrollState: LazyListState) {
    val maxOffset = with(LocalDensity.current) {
        ImageExpanded.roundToPx()
    } - LocalWindowInsets.current.systemBars.layoutInsets.top

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = Color(0xFF050B18),
        modifier = Modifier
            .height(
                AppBarExpendedHeight
            )
            .offset {
                IntOffset(x = 0, y = -offset)
            },
        elevation = if (offset == maxOffset) 4.dp else 0.dp
    ) {
        Box(modifier = Modifier) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ImageExpanded)
                ) {
                    Image(
                        modifier = Modifier
                            .height(294.dp)
                            .fillMaxWidth(),
                        painter = painterResource(id = R.drawable.dota_back_image),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color(0xFF050B18), Color(0x00050B18)
                                    )
                                )
                            )
                            .fillMaxWidth()
                            .height(126.dp)
                    )
                }
                val iconOffset = 48.dp + 88.dp * (1f - offsetProgress)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = iconOffset),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = appData.name,
                        fontSize = 20.sp,
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .scale(1f - 0.25f * offsetProgress),
                        fontFamily = fonts
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .alpha(1f - offsetProgress)
                    ) {
                        RatingBar(
                            rating = appData.rating.value
                        )
                        Text(
                            text = "70M",
                            fontSize = 12.sp,
                            letterSpacing = 0.5.sp,
                            color = Color(0xFF45454D),
                        )
                    }
                }
            }
            Image(
                modifier = Modifier
                    .padding(start = 24.dp, top = 270.dp)
                    .scale(1f - offsetProgress)
                    .alpha(1f - offsetProgress)
                    .size(88.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFF1F2430), RoundedCornerShape(12.dp)),
                painter = painterResource(id = R.drawable.dota_icon),
                contentDescription = null
            )
        }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(AppBarCollapsedHeight + 24.dp)
            .padding(start = 24.dp, top = 24.dp, end = 24.dp)
    ) {
        ActionButton(
            modifier = Modifier
                .scale(1f - offsetProgress)
                .alpha(1f - offsetProgress), icon = AppIcons.Back
        )
        ActionButton(
            modifier = Modifier
                .scale(1f - offsetProgress)
                .alpha(1f - offsetProgress), icon = AppIcons.Dots
        )
    }
}

@Composable
fun Description(modifier: Modifier = Modifier, description: String) {
    Text(
        modifier = modifier,
        text = description,
        textAlign = TextAlign.Start,
        fontSize = 12.sp,
        letterSpacing = 0.sp,
        lineHeight = 19.sp,
        color = Color(0xFFA8ADB7),
    )

}

@Composable
fun Tags(modifier: Modifier = Modifier, tags: List<String>) {
    Row(modifier = modifier) {
        for (tag in tags) {
            Tag(Modifier.padding(end = 10.dp), text = tag.uppercase(Locale.ENGLISH))
        }
    }
}

@Composable
fun MediaList(modifier: Modifier = Modifier, list: List<AppData.Media>) {
    LazyRow(
        contentPadding = PaddingValues(
            start = 24.dp,
            top = 24.dp,
            end = 24.dp,
            bottom = 24.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(list) {
            Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                Image(
                    modifier = Modifier
                        .size(width = 240.dp, height = 128.dp)
                        .clip(RoundedCornerShape(14.dp)),
                    painter = painterResource(id = it.thumb),
                    contentDescription = ""
                )
                if (it.type == AppData.Type.Video) {
                    ActionButton(icon = AppIcons.Play)
                }
            }
        }
    }
}
@Composable
fun Content(appData: AppData, scrollState: LazyListState) {
    LazyColumn(contentPadding = PaddingValues(top = AppBarExpendedHeight), state = scrollState) {
        //todo divide on items
        item {
            Column(
            ) {
                Tags(
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
                    tags = appData.tags
                )
                Description(
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
                    description = appData.description
                )
                MediaList(list = appData.media)
                Rating(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp),
                    rating = appData.rating
                )
                for (review in appData.reviews) {
                    Review(modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp, bottom = 24.dp),
                        review = review)
                }

                Button(onClick = {  }) {
                    Text(text = "Install")
                }
            }
        }
    }
}

@Composable
fun Review(modifier: Modifier = Modifier, review: AppData.Review) {
    Column(modifier = modifier) {
        Row() {
            Image(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
                painter = painterResource(id = review.author.avatar),
                contentDescription = "Avatar"
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = review.author.name,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    letterSpacing = 0.5.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White,
                )
                Text(
                    text = review.date,
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    letterSpacing = 0.5.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF696D74),
                )

            }
        }
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = review.text,
            textAlign = TextAlign.Start,
            fontSize = 12.sp,
            letterSpacing = 0.5.sp,
            lineHeight = 20.sp,
            color = Color(0xFFA8ADB7)
        )

    }
}




