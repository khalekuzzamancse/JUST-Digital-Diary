package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.calender.Calender
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.calender.CalenderFactory
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.calender.MonthName

@Composable
fun HomeDestination(
    onEvent: (HomeDestinationEvent) -> Unit,
) {
    val month = MonthName.March
    Column(
        modifier = Modifier.width(IntrinsicSize.Min)
    ) {
        Text(CalenderFactory.currentYear)
        MonthNameDisplayer(month.name)
        Calender(
            month = month,
            onDayClick = {
            }
        )
    }


}


data class DateInfo(
    val date: Int,
    val isHoliday: Boolean = false,
    val reason: String? = null
)


@Composable
private fun MonthNameDisplayer(
    monthName: String
) {
    Surface(
        shadowElevation = 8.dp,
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = monthName,
                color = Color.White
            )
        }

    }

}


fun isColorDark(color: Color): Boolean {
    val luminance = 0.299 * color.red + 0.587 * color.green + 0.114 * color.blue
    return luminance < 0.5
}