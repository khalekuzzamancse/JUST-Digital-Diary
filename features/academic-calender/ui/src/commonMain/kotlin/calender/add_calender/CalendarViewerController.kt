package calender.add_calender

import calender.interface_adapter.MonthDataUiModel
import kotlinx.coroutines.flow.StateFlow

interface CalendarViewerController {
    val currentMonthCalendar: StateFlow<MonthDataUiModel?>
    val currentMonthOrdinal:Int
    fun setYearData(yearData: List<MonthDataUiModel>)
    fun goToNextMonth()
    fun goToPreviousMonth()
}