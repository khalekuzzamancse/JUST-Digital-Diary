package administration.ui.officers.components

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal object  CardTypography{
    val title = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    fontFamily = FontFamily.Monospace
    )
    val subTitle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        fontFamily = FontFamily.Monospace
    )
    val title2 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default,
    )

    val contactStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        fontFamily = FontFamily.Monospace
    )


}