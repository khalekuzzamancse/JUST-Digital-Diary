@file:Suppress("VariableName", "FunctionName")

package calendar.ui.admin

import calendar.presentationlogic.model.CalendarGridCell
import calendar.presentationlogic.model.HolidayType
import calendar.presentationlogic.model.MonthData
import calendar.ui.component.CalendarGridManager
import calendar.ui.component.CalendarViewerController
import feature.academiccalender.domain.model.CalendarModel
import feature.academiccalender.domain.usecase.ReadAcademicCalenderUseCase
import feature.academiccalender.domain.usecase.ReadRawCalenderUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    private val readRawUseCase: ReadRawCalenderUseCase,
    private val readAcademicUseCase:ReadAcademicCalenderUseCase,
    private val presenter: CalendarGridManager
) : HolidayEditorController {
    private var calender: CalendarModel? = null
    override val currentMonthData = viewerController.currentMonthCalendar


    private val _year = MutableStateFlow<Int?>(null)
    override val currentYear = _year.asStateFlow()

    override val selectedDates = selectionController.selectedCalendarCells

    private var _yearData: List<MonthData> = emptyList()



    init {
        CoroutineScope(Dispatchers.Default).launch {
            _fetchCalender()
            calender?.let { calender ->
                _year.update { calender.year }
                _updateYearData(calender)
                viewerController.setYearData(_yearData)
            }
        }

    }

    override fun getYearData()= _yearData


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
            //Update calendar
            _yearData = command.execute(_yearData, dateOrdinals, reason, type, currentMonth)
            viewerController.setYearData(_yearData)

        } catch (_: Exception) {

        }
    }

    override fun goToNextMonthCalender() = viewerController.goToNextMonth()
    override fun goToPreviousMonthCalender() = viewerController.goToPreviousMonth()

    //TODO: state updated method section----------
    //TODO: state updated method section----------


    private suspend fun _fetchCalender() {
        //Try to read the academic calendar first so that admin can update it
        //If academic calendar not found then fetch the raw calender so that admin can add holiday and insert
        readAcademicUseCase
            .execute()
            .onSuccess {
                calender = it
            }
            .onFailure {
                readRawUseCase.execute()
                    .onSuccess {
                        calender = it
                    }
                    .onFailure {
                        calender = null
                    }
            }


    }

    private fun _updateYearData(model: CalendarModel) {
        _yearData = presenter.buildMonthGrid(model)
    }
}

