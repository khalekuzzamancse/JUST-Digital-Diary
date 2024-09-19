package calender.ui.calender

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AcademicCalender(
    modifier: Modifier = Modifier,
    monthBuilder: CalenderFactory.MonthCalenderBuilder,
    currentYear: String,
    currentMonth: String,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    onDayClick: (day: Int) -> Unit,
) {


    Column(
        modifier = modifier.width(IntrinsicSize.Min)
    ) {
        Surface(
            shadowElevation = 6.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                _CalenderTitle(currentYear)
            }

        }

        Surface(
            shadowElevation = 0.dp,
        ) {
            _CalenderMonthHeader(
                modifier = Modifier,
                monthName = currentMonth,
                onNext = onNext,
                onPrev = onPrev
            )
        }

        _Calender(
            builder = monthBuilder,
            onDayClick = onDayClick
        )
    }


}

@Composable
private fun _CalenderMonthHeader(
    modifier: Modifier,
    monthName: String,
    onNext: () -> Unit,
    onPrev: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        _PrevButton(onClick = onPrev)
        _MonthNameDisplayed(
            modifier = Modifier
                .padding(4.dp).weight(1f)
                .align(Alignment.CenterVertically),
            name = monthName,

            )
        _NextButton(onClick = onNext)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun _CalenderTitle(
    currentYear: String
) {
    FlowRow {
        Text("Academic Calender ")
        Text(currentYear)
    }


}

@Composable
private fun _PrevButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowCircleLeft,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun _NextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            Icons.Default.ArrowCircleRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}


@Composable
private fun _MonthNameDisplayed(
    modifier: Modifier,
    name: String
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Text(
            text = name
        )

    }


}

@Composable
private fun _Calender(builder: CalenderFactory.MonthCalenderBuilder, onDayClick: (day: Int) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        for (currentColumn in CalenderFactory.FIRST_COLUMN until CalenderFactory.COLUMN_COUNT) {
            _EachColumn(
                builder = builder,
                dayName = CalenderFactory.dayName(currentColumn),
                currentColumn = currentColumn,
                numberOfColumns = CalenderFactory.COLUMN_COUNT,
                numberOfCells = CalenderFactory.CELL_COUNT,
                onDayClick = onDayClick
            )
        }
    }


}

@Composable
private fun _EachColumn(
    builder: CalenderFactory.MonthCalenderBuilder,
    dayName: String,
    numberOfCells: Int,
    numberOfColumns: Int,
    currentColumn: Int,
    onDayClick: (day: Int) -> Unit,
) {
    val cellElevation = 1.dp
    val isHoliday = currentColumn == 5 || currentColumn == 6//thursday,friday holiday
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(IntrinsicSize.Min),
    ) {
        Surface(
            shadowElevation = cellElevation + 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = dayName,
                )
            }

        }
        for (cellNo in currentColumn until numberOfCells step numberOfColumns) {
            _Day(
                day = builder.dateOnThisCell(cellNo),
                onDayClick = {
                    if (isHoliday)
                        onDayClick(it)
                },
                isHoliday = isHoliday,
                elevation = cellElevation,
            )
        }

    }


}

@Composable
private fun _Day(
    elevation: Dp,
    day: Int?,
    isHoliday: Boolean = false,
    onDayClick: (day: Int) -> Unit = {},
) {

    val fontSize: TextUnit = 20.sp
    val width: Dp = with(LocalDensity.current) {
        fontSize.toDp() * 2//max 2 digit date
    }
    Surface(
        shadowElevation = elevation,
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