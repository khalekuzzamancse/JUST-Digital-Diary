package calendar.ui.public_.view_calender

import calendar.ui.component.CalendarViewerController
import calendar.presentationlogic.model.MonthData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * - Though it has no dependencies, should not create instances of it via the constructor; instead,
 * use a factory method or dependency injection (DI) to obtain an instance
 */
internal class CalendarViewerControllerImpl : CalendarViewerController {
    private var _yearData: List<MonthData> = emptyList()


    private val _currentCalendar = MutableStateFlow<MonthData?>(null)
    override val currentMonthCalendar = _currentCalendar.asStateFlow()

    private val _currentMonthOrdinal = MutableStateFlow(LocalDate.now().month.ordinal)
    override val currentMonthIndex: Int
        get() = _currentMonthOrdinal.value

    init {
        observeSelectedMonth()
    }
    override fun setYearData(yearData: List<MonthData>) {
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


    private fun MutableStateFlow<MonthData?>.updateBy(monthOrdinal: Int) {
        this.value = _yearData[monthOrdinal]
    }

    private fun observeSelectedMonth() {
        CoroutineScope(Dispatchers.Default).launch {
            _currentMonthOrdinal.collect { requestedMonth ->
                if (requestedMonth.isValidMonth() && requestedMonth.hasThisMonthData()) {
                    _currentCalendar.updateBy(requestedMonth)

                }

            }
        }
    }


    private fun Int.hasThisMonthData() = (_yearData.size > this)
    private fun Int.isValidMonth() = (this in 0..11)
}
