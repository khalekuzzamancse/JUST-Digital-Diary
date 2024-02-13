package com.just.cse.digital_diary.features.admin_office.destination.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.just.cse.digital_diary.features.admin_office.destination.R


private val Oswald = FontFamily(
    Font(R.font.oswald_light, FontWeight.Light),
    Font(R.font.oswald_regular, FontWeight.Normal),
    Font(R.font.oswald_medium, FontWeight.Medium),
    Font(R.font.oswald_semi_bold, FontWeight.SemiBold)
)

// Set of Material typography styles to start with
internal val typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Oswald,
        fontWeight = FontWeight.Light,
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Oswald,
        fontWeight = FontWeight.Light,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Oswald,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    ),
//    h4 = TextStyle(
//        fontFamily = Rubik,
//        fontWeight = FontWeight.Normal,
//        fontSize = 34.sp,
//        letterSpacing = 0.25.sp
//    ),
//    h5 = TextStyle(
//        fontFamily = Rubik,
//        fontWeight = FontWeight.Medium,
//        fontSize = 24.sp,
//        letterSpacing = 0.15.sp
//    ),
//    h6 = TextStyle(
//        fontFamily = Rubik,
//        fontWeight = FontWeight.Medium,
//        fontSize = 20.sp,
//        letterSpacing = 0.15.sp
//    ),
    titleLarge = TextStyle(
        fontFamily = Oswald,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Oswald,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Oswald,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Oswald,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
//    button = TextStyle(
//        fontFamily = Rubik,
//        fontWeight = FontWeight.Medium,
//        fontSize = 14.sp,
//        letterSpacing = 1.sp
//    ),
//    caption = TextStyle(
//        fontFamily = Rubik,
//        fontWeight = FontWeight.Normal,
//        fontSize = 12.sp,
//        letterSpacing = 0.4.sp
//    ),
//    overline = TextStyle(
//        fontFamily = Rubik,
//        fontWeight = FontWeight.Normal,
//        fontSize = 14.sp,
//        letterSpacing = 1.5.sp
//    ),
)