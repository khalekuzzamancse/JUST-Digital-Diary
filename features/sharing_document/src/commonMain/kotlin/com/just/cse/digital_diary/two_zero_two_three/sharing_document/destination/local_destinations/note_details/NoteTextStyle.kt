package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.note_details

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal object  NoteTextStyle{
    val title = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp,
    fontFamily = FontFamily.Monospace
    )

    val description = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 10.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily.Monospace
    )
      val creatorName = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 10.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily.Cursive
    )

}