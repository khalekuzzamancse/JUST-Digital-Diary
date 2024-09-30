@file:Suppress("VariableName", "FunctionName")

package calendar.ui.factory.add_calender

import calendar.ui.presenter.CalendarGridPresenter
import calendar.ui.ui.common.CalendarViewerController
import calendar.ui.ui.public_.AcademicCalenderView
import calendar.ui.ui.public_.CalendarViewController
import domain.usecase.RetrieveCalendarUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * - One of the `implementation` of [CalendarViewController]
 * - It used to manage the state and event [AcademicCalenderView]
 * - Since most of the state and event are matched with [CalendarViewController] which is used by
 * the `Calender Holiday  Editor`, taking the common state and event and hiding the remaining
 * - So just doing `delegation` ,you can think it as `wrapper` or `decorator` or `adapter`
 * - This class receives all dependencies via the constructor, making it easy to integrate
 * with Dependency Injection (DI)
 * - It depends on  abstraction so via the `factory method` any time it dependencies can be altered with different
 * implementations
 *- Should not create instances of it via the constructor; instead,
 * use a factory method or dependency injection (DI) to obtain an instance
 * - For more on `Controller` see [CalendarViewController] docs
 */
internal class CalendarViewControllerImpl(
    private val viewerController: CalendarViewerController,
    private val presenter: CalendarGridPresenter,
    private val useCase:RetrieveCalendarUseCase
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
        useCase.execute(2024)
            .onSuccess { calender ->
                val yearData =presenter.buildMonthGrid(calender)
                viewerController.setYearData(yearData)
            }
            .onFailure {

            }
    }
}
