@file:Suppress("VariableName", "FunctionName", "UnUsed")

package calendar.ui.public_

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import calendar.presentationlogic.controller.public_.CalendarViewController
import calendar.ui.component.AcademicCalender
import calendar.ui.component.ProgressBarDecorator


/**
 * - Show the academic calender
 * - It just `Adapter` or `Wrapper` or `Decorator` that wrap the [AcademicCalender] and disable it ef
 * @param onSnackBarMsgRequest parent should show the reason via snackBar
 */
@Composable
fun AcademicCalenderView(
    modifier: Modifier = Modifier,
    controller: CalendarViewController,
    onSnackBarMsgRequest: (reason: String) -> Unit,
) {
    ///Wrapping the whole UI into a box so that consumer can easily deal alignment
    //or other staff via Modifier
    /** Calender Grid cell data*/
    val monthData = controller.currentMonthCalender.collectAsState().value
    if (monthData == null) {

      //  ProgressBarDecorator(Modifier.fillMaxSize())
    } else {
        AcademicCalender(
            modifier = modifier,
            year = controller.year.collectAsState("").value.toString(),
            monthName = monthData.name,
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

