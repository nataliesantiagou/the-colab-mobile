package com.ux.thecolab.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomHeader (
    fontColor: Color,
    backgroundColor: Color,
    text: String,
) {
    Text(
        text = text,
        maxLines = 1,
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .wrapContentHeight()
            .heightIn(min = 64.dp)
            .background(color = backgroundColor.copy(alpha = 0.8f))
            .fillMaxSize()
            .padding(vertical = 25.dp),
        color = fontColor,
        fontWeight = FontWeight.SemiBold
    )
}
