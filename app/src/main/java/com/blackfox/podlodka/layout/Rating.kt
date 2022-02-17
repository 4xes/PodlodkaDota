package com.blackfox.podlodka.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.blackfox.podlodka.R
import kotlin.math.ceil

private enum class StarState {
    NotFilled, HalfFilled, Filled
}

@Composable
fun RatingBar(modifier: Modifier = Modifier, rating: Float) {
    Row(modifier = modifier) {
        for (i in 0 until 5) {
            val starState = if (rating > i) {
                if (rating < i + 1) {
                    StarState.HalfFilled
                } else {
                    StarState.Filled
                }
            } else {
                StarState.NotFilled
            }
            RatingStar(state = starState)
        }
    }
}

private object ClipHalfWidth : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline = Outline.Generic(Path().apply {
        addRect(Rect(Offset.Zero, Size(ceil(size.width * 0.5f), size.height)))
    })
}

@Composable
private fun RatingStar(state: StarState) {
    when (state) {
        StarState.NotFilled -> {
            Image(
                painter = painterResource(id = R.drawable.ic_rating_star),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color(0xFF282E3E))
            )
        }
        StarState.HalfFilled -> {
            Box(modifier = Modifier) {
                Image(
                    painter = painterResource(id = R.drawable.ic_rating_star),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color(0xFF282E3E))
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_rating_star),
                    contentDescription = "",
                    modifier = Modifier.clip(ClipHalfWidth)
                )
            }
        }
        StarState.Filled -> {
            Image(
                painter = painterResource(id = R.drawable.ic_rating_star),
                contentDescription = ""
            )
        }
    }
}