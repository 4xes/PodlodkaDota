package com.blackfox.podlodka.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blackfox.podlodka.ui.theme.fonts

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String
) {
    Text(
        modifier = modifier
            .clip(RoundedCornerShape(11.dp))
            .background(
                Color(0xFF44A9F4).copy(alpha = 0.24f)
            )
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clickable { onClick.invoke() },
        text = text,
        color = Color(0xFF41A0E7),
        fontSize = 10.sp,
        letterSpacing = 0.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = fonts
    )
}