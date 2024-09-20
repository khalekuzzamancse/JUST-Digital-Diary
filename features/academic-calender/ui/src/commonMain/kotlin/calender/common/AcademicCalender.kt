@file:Suppress("FunctionName")

package calender.common

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

/**
 * To Use as Editor:
 * - [onClick] pass non null
 * - [selected] pass the selected(that are clicked), because they need to highlight
 *
 */
@Composable
fun AcademicCalender(
    modifier: Modifier = Modifier,
    year: String,
    monthName: String,
    cellUiModels: List<CalendarCellUiModel>,
    onHolidayClick: (reason: String) -> Unit,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    //For editor mode
    selected: Set<CalendarCellUiModel>?,
    onClick: ((CalendarCellUiModel) -> Unit)?,
) {
    Column(
        modifier = modifier.width(IntrinsicSize.Min)
    ) {
        _YearNMonthHeader(
            modifier = Modifier,
            year = year,
            monthName = monthName,
            onNext = onNext,
            onPrev = onPrev
        )
        _CalenderGrid(
            calenderCellUiModels = cellUiModels,
            onHolidayClick = onHolidayClick,
            selected=selected,
            onClick = onClick
        )
    }
}


/**
 * - Contain the Title of year, Month
 * - Has next and prev button for navigating between previous month and next month calender
 */
@Composable
private fun _YearNMonthHeader(
    modifier: Modifier = Modifier,
    year: String,
    monthName: String,
    onNext: () -> Unit,
    onPrev: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        _YearHeader(modifier = Modifier, year = year)
        _MonthHeader(
            modifier = Modifier,
            monthName = monthName,
            onNext = onNext,
            onPrev = onPrev
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun _YearHeader(modifier: Modifier = Modifier, year: String) {
    Surface(
        shadowElevation = 6.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            FlowRow {
                Text("Academic Calender ")
                Text(year)
            }
        }

    }
}

//TODO: Calender Grid section  Section--------------------------
//TODO: Calender Grid section  Section--------------------------

/**
 * @param onHolidayClick parent should show the reason via snackBar
 * @param onClick pass null if want to disable click the non-holiday cell,pass lambda when using as editor
 * because in editor every date should be clickable
 */
@Composable
private fun _CalenderGrid(
    calenderCellUiModels: List<CalendarCellUiModel>,
    onHolidayClick: (reason: String) -> Unit,
    //For editor mode
    selected: Set<CalendarCellUiModel>?,
    onClick: ((CalendarCellUiModel) -> Unit)?,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        for (currentColumn in 0 until 7) {//Sat-Friday=7 days ,index 0 to 6
            _EachColumn(
                currentColumn = currentColumn,
                calenderCellUiModels = calenderCellUiModels,
                onHolidayClick = onHolidayClick,
                selected = selected,
                onClick = onClick,

                dayName = when (currentColumn) {
                    0 -> "Sat"
                    1 -> "Sun"
                    2 -> "Mon"
                    3 -> "Tue"
                    4 -> "Wed"
                    5 -> "Thu"
                    6 -> "Fri"
                    else -> "Invalid day"
                }
            )
        }
    }


}


/**
 * - Should be pass 35 Cell, 7*5 where 7 days=Sat to Fri; at most 31 days in 35 cell is enough
 * To Use as Editor:
 * - [onClick] pass non null
 * - [selected] pass the selected(that are clicked), because they need to highlight
 *  @param onHolidayClick parent should show the reason via snackBar
 */
@Composable
private fun _EachColumn(
    calenderCellUiModels: List<CalendarCellUiModel>,
    currentColumn: Int,
    dayName: String,
    onHolidayClick: (reason: String) -> Unit,
//For editor mode
    selected: Set<CalendarCellUiModel>?,
    onClick: ((CalendarCellUiModel) -> Unit)?,
) {
    val cellElevation = 1.dp
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(IntrinsicSize.Min),
    ) {
        _ColumnName(
            modifier = Modifier.fillMaxWidth(),
            name = dayName,
            cellElevation = cellElevation + 2.dp
        )
        _ColumnContent(
            modifier = Modifier,
            currentColumn = currentColumn,
            cellsInfo = calenderCellUiModels,
            cellElevation = cellElevation,
            onHolidayClick = onHolidayClick,
            onClick = onClick,
            selected = selected
        )

    }


}

/**
 * - Represent the date on single column such as all dates that in the column=Fri day
 * To Use as Editor:
 * - [onClick] pass non null
 * - [selected] pass the selected(that are clicked), because they need to highlight
 * @param onHolidayClick parent should show the reason via snackBar
 *
 */
@Composable
private fun _ColumnContent(
    modifier: Modifier = Modifier,
    currentColumn: Int,
    cellsInfo: List<CalendarCellUiModel>,
    cellElevation: Dp,
    onHolidayClick: (reason: String) -> Unit,
    //For editor mode
    selected:Set<CalendarCellUiModel>?,
    onClick: ((CalendarCellUiModel) -> Unit)?,
) {
    val fontSize: TextUnit = 20.sp
    val width: Dp = with(LocalDensity.current) { fontSize.toDp() * 2 }//max 2 digit date

    //cellNo are, x, x+7,x+7+7,...
    for (cellNo in currentColumn until 35 step 7) {
        val day = cellsInfo.find { it.cellNo == cellNo }
        val hasDateAssociatedWithThisCell = (day?.dayOrdinal != null)
        val isHoliday = (day?.holiday != null)
        val inEditorMode = (onClick != null)
        val isSelected=(selected?.find { it==day }!=null)
        val cellColor:Color= if (isSelected)MaterialTheme.colorScheme.secondaryContainer else Color.Unspecified

        _GridCell(
            date = {
                //Put a date on the cell if it has a date associated with
                if (hasDateAssociatedWithThisCell) {
                    _DateLabel(
                        modifier = it,
                        dateOrdinal = day?.dayOrdinal,
                        textColorHex = day?.holiday?.colorHexCode,
                        fontSize = fontSize
                    )
                }
            },
            modifier = Modifier.height(width)//height is enough as 2 char width
                .width(width)
                .background(cellColor)
                .then(
                    when {
                        //When editor mode, do not need to show the reason that is  check if editor mode
                        //then do not show reason
                        hasDateAssociatedWithThisCell && inEditorMode -> Modifier.clickable {
                            if (onClick != null && day != null) {
                                onClick(day)
                            }
                        }

                        isHoliday -> Modifier.clickable {
                            day?.holiday?.let { onHolidayClick(it.reason) }
                        }

                        else -> Modifier
                    }
                ),
            elevation = cellElevation,
        )
    }

}

/**
 * @param  name are Sat,Sun.....
 */
@Composable
private fun _ColumnName(
    modifier: Modifier = Modifier,
    cellElevation: Dp,
    name: String
) {
    Surface(
        shadowElevation = cellElevation,
        modifier = modifier
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = name,
            )
        }

    }
}

@Composable
private fun _DateLabel(
    modifier: Modifier = Modifier,
    dateOrdinal: Int?,
    textColorHex: String?,
    fontSize: TextUnit,
) {
    val color = if (textColorHex != null) _parseColor(textColorHex)
        ?: Color.Unspecified else Color.Unspecified
    Text(
        text = if (dateOrdinal == null) "" else "$dateOrdinal",
        fontSize = fontSize,
        color = color,
        modifier = modifier
    )
}

/**
 *  * @param holiday null when this day is not a holiday
 *  @param date represent the date original 1,2...31 , null when cell has no date associated
 */
@Composable
private fun _GridCell(
    elevation: Dp,
    modifier: Modifier,
    date: (@Composable (Modifier) -> Unit)? = null
) {
    Surface(
        shadowElevation = elevation,
        modifier = Modifier
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center,
        ) {
            if (date != null)
                date(Modifier)
        }
    }


}

private fun _parseColor(hex: String): Color? {
    return try {
        val normalizedHex = hex.removePrefix("#")
        if (normalizedHex.length == 6 || normalizedHex.length == 8) {
            val colorLong = normalizedHex.toLong(16)
            if (normalizedHex.length == 6) {
                Color(color = 0xFF000000 or colorLong)
            } else {
                Color(color = colorLong)
            }
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}

//TODO: Month header Section--------------------------
//TODO: Month header Section--------------------------
@Composable
private fun _MonthHeader(
    modifier: Modifier,
    monthName: String,
    onNext: () -> Unit,
    onPrev: () -> Unit,
) {
    Surface(
        shadowElevation = 0.dp,
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            _PrevButton(onClick = onPrev)
            //Month name
            Box(
                modifier = Modifier.padding(4.dp).weight(1f).align(Alignment.CenterVertically),
                contentAlignment = Alignment.Center
            ) {
                Text(text = monthName)

            }
            _NextButton(onClick = onNext)
        }
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
