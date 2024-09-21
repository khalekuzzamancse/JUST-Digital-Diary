@file:Suppress("VariableName", "FunctionName", "UnUsed")

package calendar.ui.viewer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import calendar.ui.common.AcademicCalender
import calendar.ui.common.ProgressBar
import calendar.ui.common.model.MonthData
import kotlinx.coroutines.flow.StateFlow

/**
 * - Manage the state and event of the [AcademicCalender]
 * - ViewModel can implement it(optional)
 */
interface CalendarViewController {
    val currentMonthCalender: StateFlow<MonthData?>
    val year: StateFlow<Int?>
    /**Update the [currentMonthCalender] with next month*/
    fun goToNextMonthCalender()

    /**Update the [currentMonthCalender] with previous  month*/
    fun goToPreviousMonthCalender()
}

/**
 * - Show the academic calender
 * - It just `Adapter` or `Wrapper` or `Decorator` that wrap the [AcademicCalender] and disable it ef
 * @param onSnackBarMsgRequest parent should show the reason via snackBar
 */
@Composable
fun AcademicCalenderViewer(
    modifier: Modifier = Modifier,
    controller: CalendarViewController,
    onSnackBarMsgRequest: (reason: String) -> Unit,
) {
    /** Calender Grid cell data*/
    val monthData = controller.currentMonthCalender.collectAsState().value
    if (monthData == null) {
        ProgressBar()
    } else {
        AcademicCalender(
            year = controller.year.collectAsState("").value.toString(),
            monthName =monthData.name,
            onNext = controller::goToNextMonthCalender,
            onPrev = controller::goToPreviousMonthCalender,
            cellUiModels = monthData.cells,
            onHolidayClick = onSnackBarMsgRequest,
            //use as viewer
            selected = null,
            onClick = null
        )
    }


}

