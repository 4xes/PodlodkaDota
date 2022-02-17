package com.blackfox.podlodka

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blackfox.podlodka.data.AppData
import com.blackfox.podlodka.layout.RatingBar
import com.blackfox.podlodka.ui.theme.fonts

@Preview
@Composable
fun DotaHomeworkCompose() {
    Rating(rating = AppData.Rating(rating = 2.9f, countReviews = "70M"))
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
            .padding(top = 12.dp)
            .background(color = Color(0x250000FF)), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = rating.rating.toString(),
                textAlign = TextAlign.Start,
                fontSize = 48.sp,
                letterSpacing = 0.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = fonts
            )
            Column(modifier = Modifier.padding(start = 16.dp).background(color = Color(0x25FF00FF))) {
                RatingBar(rating = rating.rating)
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
