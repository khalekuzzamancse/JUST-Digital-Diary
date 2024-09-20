@file:Suppress("VariableName", "FunctionName")

package calender.interface_adapter

import calender.add_calender.HolidayAddCommand
import calender.add_calender.HolidayEditorUiController
import calender.add_calender.HolidayTypeUiModel
import calender.common.CalenderCellUiModel
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
import java.time.LocalDate

internal class HolidayEditorUiControllerImpl : HolidayEditorUiController {
    private var calender: CalendarModel? = null
    private val _currentCalender = MutableStateFlow<List<CalenderCellUiModel>?>(null)
    override val currentMonthCalender = _currentCalender.asStateFlow()
    private var _currentMonthOrdinal =
        MutableStateFlow(LocalDate.now().month.ordinal) //from jan to dec (0 to 11)

    private val _monthName = MutableStateFlow(LocalDate.now().month.name)
    override val monthName = _monthName.asStateFlow()
    private val _year = MutableStateFlow<Int?>(null)
    override val year = _year.asStateFlow()
    private val _selectedDates = MutableStateFlow<Set<CalenderCellUiModel>>(emptySet())
    override val selected = _selectedDates.asStateFlow()

    private var _yearData: List<List<CalenderCellUiModel>> = emptyList()


    init {
        CoroutineScope(Dispatchers.Default).launch {
            delay(2_000)//pretending loading...
            fetchCalender()
            calender?.let { calender ->
                _year.update { calender.year }
                _updateAllMonthData(calender)
                _currentCalender._updateBy(_currentMonth())
            }
        }
        _observeSelectedMonth()

    }


    //TODO:Events handler
    override fun onSelectionRequest(cell: CalenderCellUiModel) {
        val alreadySelected = (_selectedDates.value.find { it == cell } != null)

        if (alreadySelected)
            _selectedDates._removeFromSelection(cell)
        else
            _selectedDates._addToSelection(cell)

    }


    override fun onHolidayConfirm(reason: String, type: HolidayTypeUiModel) {
        _updateHoliday(_selectedDates.value.mapNotNull { it.dayOrdinal }, reason, type)
        _selectedDates.update { emptySet() }
    }

    private fun _updateHoliday(dateOrdinals: List<Int>, reason: String, type: HolidayTypeUiModel) {
        try {
            _yearData = HolidayAddCommand(_yearData, dateOrdinals, reason, type, _currentMonth()).execute()
            _currentCalender._updateBy(_currentMonth())

        } catch (_: Exception) {

        }
    }

    override fun goToNextMonthCalender() =
        _updateCalenderCurrentIndexIfValid(_currentMonthOrdinal.value + 1)

    override fun goToPreviousMonthCalender() =
        _updateCalenderCurrentIndexIfValid(_currentMonthOrdinal.value - 1)


    //TODO: state updated method section----------
    //TODO: state updated method section----------

    private fun _updateCalenderCurrentIndexIfValid(requestedMonthOrdinal: Int) {
        if (requestedMonthOrdinal._isValidMonth())
            _currentMonthOrdinal.update { requestedMonthOrdinal }
    }

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

    private fun _updateAllMonthData(model: CalendarModel) {
        _yearData = CalendarUIGridModelPresenter().buildMonthGrid(model)
    }

    /*Just update, neither throw exception or check validity*/
    private fun MutableStateFlow<List<CalenderCellUiModel>?>._updateBy(monthOrdinal: Int) =
        this.update { _yearData[monthOrdinal] }

    private fun _observeSelectedMonth() {
        CoroutineScope(Dispatchers.Default).launch {
            _currentMonthOrdinal.collect { requestMonth ->//Jan-Dec ( 0-11)
                if (requestMonth._isValidMonth() && requestMonth._hasThisMonthData()) {
                    _currentCalender._updateBy(requestMonth)
                    _updateMonth(requestMonth)
                }
            }
        }
    }

    //TODO:Helper method section-----------
    //TODO:Helper method section-----------
    private fun _updateMonth(index: Int) =
        _monthName.update { calender?.months?.get(index)?.month.toString() }

    private fun MutableStateFlow<Set<CalenderCellUiModel>>._addToSelection(cell: CalenderCellUiModel) =
        this.update { it + cell }

    private fun MutableStateFlow<Set<CalenderCellUiModel>>._removeFromSelection(cell: CalenderCellUiModel) =
        this.update { it - cell }

    private fun _currentMonth() = _currentMonthOrdinal.value

    private fun Int._hasThisMonthData() = (_yearData.size > this)
    private fun Int._isValidMonth() = (this in 0..11)
}
