@file:Suppress("VariableName", "FunctionName")

package calendar.ui.factory

import calendar.ui.common.CalendarViewerController
import calendar.ui.editor.HolidayAddCommand
import calendar.ui.editor.HolidayEditorController
import calendar.ui.editor.HolidayDateSelector
import calendar.ui.common.model.CalendarGridCell
import calendar.ui.common.model.HolidayType
import calendar.ui.common.CalendarUIGridModelPresenter
import calendar.ui.common.model.MonthData
import di.DIFactory
import domain.model.CalendarModel
import domain.model.DayOfWeek
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


internal class EditorControllerImpl(
    private val selectionController: HolidayDateSelector,
    private val viewerController: CalendarViewerController
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
            fetchCalender()
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
            _yearData =
                HolidayAddCommand(_yearData, dateOrdinals, reason, type, currentMonth).execute()
            viewerController.setYearData(_yearData)

        } catch (_: Exception) {

        }
    }

    override fun goToNextMonthCalender() = viewerController.goToNextMonth()
    override fun goToPreviousMonthCalender() = viewerController.goToPreviousMonth()

    //TODO: state updated method section----------
    //TODO: state updated method section----------


    private suspend fun fetchCalender() {
        DIFactory
            .createRawRetrieveCalenderUseCase()
            .execute(2024, weekend = listOf(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY))
            .onSuccess {
                calender = it

            }
            .onFailure {
                calender = null
            }
    }

    private fun _updateYearData(model: CalendarModel) {
        _yearData = CalendarUIGridModelPresenter()._buildMonthGrid(model)
    }
}

