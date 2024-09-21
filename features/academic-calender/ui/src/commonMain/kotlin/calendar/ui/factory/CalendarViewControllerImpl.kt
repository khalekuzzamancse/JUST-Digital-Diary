@file:Suppress("VariableName", "FunctionName")

package calendar.ui.factory

import calendar.ui.common.CalendarViewerController
import calendar.ui.common.CalendarUIGridModelPresenter
import calendar.ui.viewer.CalendarViewController
import di.DIFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import calendar.ui.viewer.AcademicCalenderViewer
/**
 * - One of the `implementation` of [CalendarViewController]
 * - It used to manage the state and event [AcademicCalenderViewer]
 * - Since most of the state and event are matched with [CalendarViewController] which is used by
 * the `Calender Holiday  Editor`, taking the common state and event and hiding the remaining
 * - So just doing `delegation` ,you can think it as `wrapper` or `decorator` or `adapter`
 */
internal class CalendarViewControllerImpl(
    private val viewerController: CalendarViewerController
) : CalendarViewController {
    override val currentMonthCalender = viewerController.currentMonthCalendar
    private val _year = MutableStateFlow<Int?>(null)
    override val year = _year.asStateFlow()

    init {
        CoroutineScope(Dispatchers.Default).launch {
            delay(2_000)//pretending loading...
            loadCalender()
        }

    }

    override fun goToNextMonthCalender() = viewerController.goToNextMonth()
    override fun goToPreviousMonthCalender() = viewerController.goToPreviousMonth()
    private suspend fun loadCalender() {
        DIFactory
            .createAcademicRetrieveCalenderUseCase()
            .execute(2024)
            .onSuccess { calender ->
                val yearData = CalendarUIGridModelPresenter()._buildMonthGrid(calender)
                viewerController.setYearData(yearData)
            }
            .onFailure {

            }
    }
}
