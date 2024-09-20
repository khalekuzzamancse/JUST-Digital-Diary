@file:Suppress("VariableName", "FunctionName", "UnUsed")

package calender.ui.calender

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import calender.common.AcademicCalender
import calender.common.CalendarCellUiModel
import calender.common.LoadingUI
import kotlinx.coroutines.flow.StateFlow

/**
 * - Manage the state and event of the [AcademicCalenderUI]
 * - ViewModel can implement it(optional)
 */
interface CalendarController {
    val currentMonthCalender: StateFlow<List<CalendarCellUiModel>?>

    /**name of the month that calender is currently showing*/
    val monthName: StateFlow<String>
    val year: StateFlow<Int?>

    /**Update the [currentMonthCalender] with next month*/
    fun goToNextMonthCalender()

    /**Update the [currentMonthCalender] with previous  month*/
    fun goToPreviousMonthCalender()
}

/**
 * @param onSnackBarMsgRequest parent should show the reason via snackBar
 */
@Composable
fun AcademicCalenderUI(
    modifier: Modifier = Modifier,
    controller: CalendarController,
    onSnackBarMsgRequest: (reason: String) -> Unit,
) {
    /** Calender Grid cell data*/
    val cellData = controller.currentMonthCalender.collectAsState().value
    if (cellData == null) {
        LoadingUI()
    } else {
        AcademicCalender(
            year = controller.year.collectAsState("").value.toString(),
            monthName = controller.monthName.collectAsState().value,
            onNext = controller::goToNextMonthCalender,
            onPrev = controller::goToPreviousMonthCalender,
            cellUiModels = cellData,
            onHolidayClick = onSnackBarMsgRequest,
            //use as viewer
            selected = null,
            onClick = null
        )
    }


}

