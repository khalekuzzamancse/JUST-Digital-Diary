@file:Suppress("VariableName","FunctionName")
package calender.ui.calender.nedw

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import calender.ui.calender.CalenderFactory
import di.DIFactory
import domain.model.CalendarModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

class CalenderController {
    private var calender: CalendarModel? = null
    private val _currentMonthCalender = MutableStateFlow<CalendarGrid?>(null)
    val currentMonthCalender = _currentMonthCalender.asStateFlow()
    private var currentMonthIndex = LocalDate.now().month.ordinal//from jan to dec (0 to 11)
    private val _monthName = MutableStateFlow(LocalDate.now().month.name)
    val monthName = _monthName.asStateFlow()
    private  val _year=MutableStateFlow<Int?>(null)
    val year=_year.asStateFlow()



    init {
        loadCalender()
        updateCalender(currentMonthIndex)
    }

    fun goToNextMonthCalender() {
        val nextMonthOrdinal = currentMonthIndex + 1
        if (nextMonthOrdinal._isValidMonth()) {
            updateCalender(nextMonthOrdinal)
            currentMonthIndex = nextMonthOrdinal
        }


    }

    fun goToPreviousMonthCalender() {
        val prevMonthOrdinal = currentMonthIndex - 1
        if (prevMonthOrdinal._isValidMonth()) {
            updateCalender(prevMonthOrdinal)
            currentMonthIndex = prevMonthOrdinal
        }

    }


    private fun loadCalender() {
        DIFactory
            .createRetrieveCalenderUseCase()
            .execute(2024)
            .onSuccess {
                calender = it
            }
            .onFailure {
                calender = null
            }
    }

    private fun updateCalender(monthOrdinal: Int) {
        calender?.let { calender ->
            val month=calender.months[monthOrdinal]
            _monthName.update { month.month.name}
            _currentMonthCalender.update {
                CalendarGridBuilder().buildMonthGrid(month)
            }
            _year.update { calender.year }
        }

    }

    private fun Int._isValidMonth() = (this in 0..11)
}


@Composable
fun AcademicCalender(
    modifier: Modifier = Modifier,
) {
    val controller = remember { CalenderController() }
    val grid = controller.currentMonthCalender.collectAsState().value
    val year=controller.year.collectAsState("").value
    val month=controller.monthName.collectAsState().value

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
                monthName =month ,
                onNext = controller::goToNextMonthCalender,
                onPrev = controller::goToPreviousMonthCalender
            )
        }

        grid?.let {
            _Calender(
                grid = it,
            )
        }

    }


}

@Composable
fun _Calender(
    grid: CalendarGrid,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        for (currentColumn in 0 until 7) {
            _EachColumn(
                dayName = CalenderFactory.dayName(currentColumn),
                currentColumn = currentColumn,
                grid = grid
            )
        }
    }


}


@Composable
private fun _EachColumn(
    grid: CalendarGrid,
    dayName: String,
    currentColumn: Int,
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
        //cellNo are, x, x+7,x+7+7,...
        for (cellNo in currentColumn until 35 step 7) {
            val day = grid.cells.find { it.cellNo == cellNo }?.day
            _Day(
                day = day?.date,
                onDayClick = {
                    day?.let {
                        if (it.isHoliday) {
                            println(it.holiday)
                        }
                    }
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
        modifier = if (day == null||!isHoliday)
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