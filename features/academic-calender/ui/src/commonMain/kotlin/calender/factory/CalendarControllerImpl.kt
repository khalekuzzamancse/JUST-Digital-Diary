package calender.factory

import calender.add_calender.CalendarViewerController
import calender.interface_adapter.MonthDataUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class CalendarControllerImpl : CalendarViewerController {
    private var _yearData: List<MonthDataUiModel> = emptyList()

    private val _currentCalendar = MutableStateFlow<MonthDataUiModel?>(null)
    override val currentMonthCalendar = _currentCalendar.asStateFlow()

    private val _currentMonthOrdinal = MutableStateFlow(LocalDate.now().month.ordinal)
    override val currentMonthOrdinal: Int
        get() = _currentMonthOrdinal.value

    init {
        observeSelectedMonth()
    }
    override fun setYearData(yearData: List<MonthDataUiModel>) {
        _yearData = yearData
        _currentCalendar.updateBy(_currentMonthOrdinal.value)

    }
    override fun goToNextMonth() {
        updateCalendarCurrentIndexIfValid(_currentMonthOrdinal.value + 1)
    }

    override fun goToPreviousMonth() {
        updateCalendarCurrentIndexIfValid(_currentMonthOrdinal.value - 1)
    }


    private fun updateCalendarCurrentIndexIfValid(requestedMonthOrdinal: Int) {
        if (requestedMonthOrdinal.isValidMonth())
            _currentMonthOrdinal.value = requestedMonthOrdinal
    }


    private fun MutableStateFlow<MonthDataUiModel?>.updateBy(monthOrdinal: Int) {
        this.value = _yearData[monthOrdinal]
    }

    private fun observeSelectedMonth() {
        CoroutineScope(Dispatchers.Default).launch {
            _currentMonthOrdinal.collect { requestedMonth ->
                println(requestedMonth)
                if (requestedMonth.isValidMonth() && requestedMonth.hasThisMonthData()) {
                    _currentCalendar.updateBy(requestedMonth)

                }

            }
        }
    }


    private fun Int.hasThisMonthData() = (_yearData.size > this)
    private fun Int.isValidMonth() = (this in 0..11)
}
