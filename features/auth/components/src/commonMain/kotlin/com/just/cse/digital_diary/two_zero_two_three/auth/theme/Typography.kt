package com.just.cse.digital_diary.two_zero_two_three.auth.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.just.cse.digital_diary.two_zero_two_three.auth.authModuleFont

val typography = Typography(


)

@Composable
fun getAuthModuleTypography(): Typography {
    val rubik = FontFamily(
        authModuleFont(
            name = "Rubik Bold",
            res = "rubik_bold",
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        ),
        authModuleFont(
            name = "Rubik Semi Bold",
            res = "rubik_semi_bold.ttf",
            weight = FontWeight.SemiBold,
            style = FontStyle.Normal
        )
    )
    return Typography(
        titleLarge = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
            fontFamily = rubik
        ),
        titleMedium = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            fontFamily = rubik
        ),
    )


}
