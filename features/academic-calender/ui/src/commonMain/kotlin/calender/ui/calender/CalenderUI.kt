@file:Suppress("VariableName", "FunctionName")

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import calender.interface_adapter.CalenderControllerImpl
import kotlinx.coroutines.flow.StateFlow


interface CalendarController {
     val currentMonthCalender: StateFlow<List<CalenderCellUiModel>?>
    val monthName: StateFlow<String>
    val year: StateFlow<Int?>

    // Methods to navigate between months
    fun goToNextMonthCalender()
    fun goToPreviousMonthCalender()
}

/**
 * @param onSnackBarMsgRequest parent should show the reason via snackBar
 */
@Composable
fun CalenderUI(
    modifier: Modifier = Modifier,
    onSnackBarMsgRequest: (reason: String) -> Unit
) {
    val controller = remember { CalenderControllerImpl() }
    val grid = controller.currentMonthCalender.collectAsState().value
    val year = controller.year.collectAsState("").value
    val month = controller.monthName.collectAsState().value

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
                _CalenderTitle(year.toString())
            }

        }

        Surface(
            shadowElevation = 0.dp,
        ) {
            _CalenderMonthHeader(
                modifier = Modifier,
                monthName = month,
                onNext = controller::goToNextMonthCalender,
                onPrev = controller::goToPreviousMonthCalender
            )
        }

        grid?.let {
            _Calender(
                calenderCellUiModels = it,
                onHolidayClick = onSnackBarMsgRequest
            )
        }

    }


}

/**
 * @param onHolidayClick parent should show the reason via snackBar
 */
@Composable
private fun _Calender(
    calenderCellUiModels: List<CalenderCellUiModel>,
    onHolidayClick: (reason: String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        for (currentColumn in 0 until 7) {//Sat-Friday=7 days ,index 0 to 6
            _EachColumn(
                currentColumn = currentColumn,
                calenderCellUiModels = calenderCellUiModels,
                onHolidayClick = onHolidayClick,
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
 *  * @param onHolidayClick parent should show the reason via snackBar
 */
@Composable
private fun _EachColumn(
    calenderCellUiModels: List<CalenderCellUiModel>,
    currentColumn: Int,
    dayName: String,
    onHolidayClick: (reason: String) -> Unit
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
            onHolidayClick = onHolidayClick
        )

    }


}

/**
 * - Represent the date on single column such as all dates that in the column=Fri day
 * @param onHolidayClick parent should show the reason via snackBar
 */
@Composable
private fun _ColumnContent(
    modifier: Modifier = Modifier,
    currentColumn: Int,
    cellsInfo: List<CalenderCellUiModel>,
    cellElevation: Dp,
    onHolidayClick: (reason: String) -> Unit
) {
    val fontSize: TextUnit = 20.sp
    val width: Dp = with(LocalDensity.current) { fontSize.toDp() * 2 }//max 2 digit date

    //cellNo are, x, x+7,x+7+7,...
    for (cellNo in currentColumn until 35 step 7) {
        val day = cellsInfo.find { it.cellNo == cellNo }
        val hasDateAssociatedWithThisCell = day != null

        _GridCell(
            date = {
                //Put a date on the cell if it has a date associated with
                if (hasDateAssociatedWithThisCell)
                    _DateLabel(
                        modifier = it,
                        dateOrdinal = day?.dayOrdinal ?: 0,
                        textColorHex = day?.holiday?.colorHexCode,
                        fontSize = fontSize
                    )

            },
            modifier = Modifier.height(width)//height is enough as 2 char width
                .width(width)
                .background(Color.White)
                .then(
                    if (day?.holiday != null) {
                        Modifier.clickable {
                            onHolidayClick(day.holiday.reason)
                        }
                    } else Modifier
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
    dateOrdinal: Int,
    textColorHex: String?,
    fontSize: TextUnit,
) {
    val color = if (textColorHex != null) _parseColor(textColorHex)
        ?: Color.Unspecified else Color.Unspecified
    Text(
        text = "$dateOrdinal",
        fontSize = fontSize,
        color = color,
        modifier = modifier
    )
}

/**
 * - Represent data or state of  a single cell of the calender Grid
 * @param dayOrdinal represent the date ordinal such  as 1,2...31, nullable because a month have at most 31 days so the remaining GridCell will be empty
 * @param dayName such as Sat,Sun...Fri
 * @param holiday null when this day is not a holiday
 **/
data class CalenderCellUiModel(
    val cellNo: Int,
    val dayOrdinal: Int? = null,
    val dayName: String,
    val holiday: HolidayUiModel? = null,
)

/**
 * - Represent the holiday
 * @param colorHexCode based on type of holiday such as AllClose,UniversityDay,Weekend ..etc pass different color
 */
data class HolidayUiModel(
    val colorHexCode: String,
    val reason: String
)

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