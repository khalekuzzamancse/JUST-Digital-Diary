@file:Suppress("VariableName", "FunctionName")

package calender.factory

import calender.add_calender.CalendarViewerController
import calender.add_calender.HolidayAddCommand
import calender.add_calender.EditorUiController
import calender.add_calender.HolidayTypeUiModel
import calender.add_calender.SelectionController
import calender.common.CalendarCellUiModel
import calender.interface_adapter.CalendarUIGridModelPresenter
import calender.interface_adapter.MonthDataUiModel
import di.DIFactory
import domain.model.CalendarModel
import domain.model.DayNameModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


internal class EditorControllerImpl(
    private val selectionController: SelectionController,
    private val viewerController: CalendarViewerController
) : EditorUiController {
    private var calender: CalendarModel? = null
    override val currentMonthCalender = viewerController.currentMonthCalendar

    private val _year = MutableStateFlow<Int?>(null)
    override val year = _year.asStateFlow()

    override val selected = selectionController.selectedCells

    private var _yearData: List<MonthDataUiModel> = emptyList()


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
    override fun onSelectionRequest(cell: CalendarCellUiModel) {
        selectionController.toggleSelection(cell)
    }


    override fun onHolidayConfirm(reason: String, type: HolidayTypeUiModel) {
        _updateHoliday(selectionController.selectedDates, reason, type)
        selectionController.clearSelection()
    }

    private fun _updateHoliday(dateOrdinals: List<Int>, reason: String, type: HolidayTypeUiModel) {
        try {
            val currentMonth = viewerController.currentMonthOrdinal
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
            .execute(2024, weekend = listOf(DayNameModel.THURSDAY, DayNameModel.FRIDAY))
            .onSuccess {
                calender = it

            }
            .onFailure {
                calender = null
            }
    }

    private fun _updateYearData(model: CalendarModel) {
        _yearData = CalendarUIGridModelPresenter().buildMonthGrid(model)
    }
}

