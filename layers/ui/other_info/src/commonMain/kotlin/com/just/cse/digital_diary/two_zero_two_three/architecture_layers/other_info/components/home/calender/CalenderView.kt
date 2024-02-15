package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.calender

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Calender(month: MonthName, onDayClick: (day: Int) -> Unit) {
    val builder = remember { CalenderFactory.MonthCalenderBuilder(month) }
    Row (
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ){
        for (currentColumn in CalenderFactory.FIRST_COLUMN until CalenderFactory.COLUMN_COUNT) {
            EachColumn(
                builder = builder,
                dayName = CalenderFactory.dayName(currentColumn),
                currentColumn = currentColumn,
                numberOfColumns = CalenderFactory.COLUMN_COUNT,
                numberOfCells = CalenderFactory.CELL_COUNT
            )
        }
    }


}

@Composable
fun EachColumn(
    builder: CalenderFactory.MonthCalenderBuilder,
    dayName: String,
    numberOfCells:Int,
    numberOfColumns:Int,
    currentColumn: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(dayName)
        for (cellNo in currentColumn until numberOfCells step numberOfColumns) {
            Day(
                day = builder.dateOnThisCell(cellNo),
                onDayClick = {}
            )
        }

    }


}

@Composable
private fun Day(
    day: Int?,
    isHoliday: Boolean = false,
    onDayClick: (day: Int) -> Unit = {},
) {

    val fontSize: TextUnit = 20.sp
    val width: Dp = with(LocalDensity.current) {
        fontSize.toDp() * 2//max 2 digit date
    }
    Surface(
        shadowElevation = 2.dp,
        modifier = if (day == null)
            Modifier else Modifier.clickable {
            onDayClick(day)
        }
    ) {
        Box(
            modifier = Modifier
                .height(width)//height is enough as 2 char width
                .width(width)
                .background(Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = if (day == null) "" else "$day",
                fontSize = fontSize,
                color = if (isHoliday) Color.Red else Color.Black,
                modifier = Modifier
            )

        }
    }


}