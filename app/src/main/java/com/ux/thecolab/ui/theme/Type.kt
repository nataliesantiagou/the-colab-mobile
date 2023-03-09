package com.ux.thecolab.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ux.thecolab.R


val openSansFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.opensans_light,
            weight = FontWeight.W300
        ),
        Font(
            resId = R.font.opensans_lightitalic,
            weight = FontWeight.W300
        ),
        Font(
            resId = R.font.opensans_regular,
            weight = FontWeight.W400
        ),
        Font(
            resId = R.font.opensans_medium,
            weight = FontWeight.W500
        ),
        Font(
            resId = R.font.opensans_semibold,
            weight = FontWeight.W600
        ),
        Font(
            resId = R.font.opensans_semibolditalic,
            weight = FontWeight.W600
        ),
        Font(
            resId = R.font.opensans_bold,
            weight = FontWeight.W700
        ),
        Font(
            resId = R.font.opensans_bolditalic,
            weight = FontWeight.W700
        ),
        Font(
            resId = R.font.opensans_extrabold,
            weight = FontWeight.W800
        ),
        Font(
            resId = R.font.opensans_extrabolditalic,
            weight = FontWeight.W800
        )

    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = openSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)