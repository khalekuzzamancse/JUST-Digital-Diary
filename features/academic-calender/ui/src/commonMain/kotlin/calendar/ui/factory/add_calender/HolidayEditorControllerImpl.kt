@file:Suppress("VariableName", "FunctionName")

package calendar.ui.factory.add_calender

import calendar.ui.model.CalendarGridCell
import calendar.ui.model.HolidayType
import calendar.ui.model.MonthData
import calendar.ui.presenter.CalendarGridPresenter
import calendar.ui.ui.admin.HolidayEditorController
import calendar.ui.ui.common.CalendarViewerController
import domain.model.CalendarModel
import domain.model.DayOfWeek
import domain.usecase.GetRawCalenderUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * - This class receives all dependencies via the constructor, making it easy to integrate
 * with Dependency Injection (DI)
 * - It depends on  abstraction so via the `factory method` any time it dependencies can be altered with different
 * implementations
 *- Should not create instances of it via the constructor; instead,
 * use a factory method or dependency injection (DI) to obtain an instance
 * - For more on `Controller` see [HolidayEditorController] docs
 */
internal class HolidayEditorControllerImpl(
    private val selectionController: HolidayDateSelector,
    private val viewerController: CalendarViewerController,
    private val command: AddHolidayCommand,
    private val useCase: GetRawCalenderUseCase,
    private val presenter: CalendarGridPresenter
) : HolidayEditorController {
    private var calender: CalendarModel? = null
    override val currentMonthData = viewerController.currentMonthCalendar

    private val _year = MutableStateFlow<Int?>(null)
    override val currentYear = _year.asStateFlow()

    override val selectedDates = selectionController.selectedCalendarCells

    private var _yearData: List<MonthData> = emptyList()


    init {
        CoroutineScope(Dispatchers.Default).launch {
            delay(2_000)//pretending loading...
            fetchRawCalender()
            calender?.let { calender ->
                _year.update { calender.year }
                _updateYearData(calender)
                viewerController.setYearData(_yearData)
            }
        }

    }


    //TODO:Events handler
    override fun onSelectionRequest(cell: CalendarGridCell) {
        selectionController.toggleDateSelection(cell)
    }


    override fun onHolidayConfirm(reason: String, type: HolidayType) {
        _updateHoliday(selectionController.selectedDays, reason, type)
        selectionController.clearSelections()
    }

    private fun _updateHoliday(dateOrdinals: List<Int>, reason: String, type: HolidayType) {
        try {
            val currentMonth = viewerController.currentMonthIndex
            _yearData = command.execute(_yearData, dateOrdinals, reason, type, currentMonth)
            viewerController.setYearData(_yearData)

        } catch (_: Exception) {

        }
    }

    override fun goToNextMonthCalender() = viewerController.goToNextMonth()
    override fun goToPreviousMonthCalender() = viewerController.goToPreviousMonth()

    //TODO: state updated method section----------
    //TODO: state updated method section----------


    private suspend fun fetchRawCalender() {
        useCase.execute(2024, weekend = listOf(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY))
            .onSuccess {
                calender = it

            }
            .onFailure {
                calender = null
            }
    }

    private fun _updateYearData(model: CalendarModel) {
        _yearData = presenter.buildMonthGrid(model)
    }
}

