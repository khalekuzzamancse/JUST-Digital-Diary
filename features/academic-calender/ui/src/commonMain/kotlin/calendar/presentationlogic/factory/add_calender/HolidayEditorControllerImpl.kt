@file:Suppress("VariableName", "FunctionName")

package calendar.presentationlogic.factory.add_calender

import calendar.presentationlogic.model.CalendarGridCell
import calendar.presentationlogic.model.HolidayType
import calendar.presentationlogic.model.MonthData
import calendar.presentationlogic.presenter.CalendarGridPresenter
import calendar.ui.admin.HolidayEditorController
import calendar.ui.common.CalendarViewerController
import domain.model.CalendarModel
import domain.model.DayOfWeek
import domain.model.Holiday
import domain.usecase.GetCalenderByYearUseCase
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
    private val useCase: GetCalenderByYearUseCase,
    private val presenter: CalendarGridPresenter
) : HolidayEditorController {
    private var calender: CalendarModel? = null
    override val currentMonthData = viewerController.currentMonthCalendar


    private val _year = MutableStateFlow<Int?>(null)
    override val currentYear = _year.asStateFlow()

    override val selectedDates = selectionController.selectedCalendarCells

    var _yearData: List<MonthData> = emptyList()
        private set


    init {
        CoroutineScope(Dispatchers.Default).launch {
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
            //Update calendar
            _yearData = command.execute(_yearData, dateOrdinals, reason, type, currentMonth)
            viewerController.setYearData(_yearData)

          val calender: List<List<Holiday>> =  _yearData.mapIndexed { index, month ->
              month.cells.mapNotNull { cell ->
                  //if has holiday on that day
                  val day = cell.dayOfMonth
                  val holiday = cell.holiday
                  if (day != null && holiday != null)
                      Holiday(
                          day = day,
                          reason = holiday.reason,
                          holidayType = when (holiday.type) {
                              HolidayType.AllOff -> domain.model.HolidayType.AllOff
                              HolidayType.SpecialDay -> domain.model.HolidayType.SpecialDay
                              HolidayType.OnlyClassOf -> domain.model.HolidayType.OnlyClassOff
                          }

                      )
                  else null
              }
          }
            calender.forEachIndexed{ index,month->
                println("${index + 1}")
                println("Calender:$month")
                println("-------")

            }


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

