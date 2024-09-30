package calendar.ui.ui.common

import calendar.ui.model.MonthData
import kotlinx.coroutines.flow.StateFlow

/**
 * - Holds the entire year's data. It provides the current month's data and, upon request, updates the current month
 *   data to the next or previous month's data (if available).
 *
 * @property currentMonthCalendar:`StateFlow<MonthDataUiModel` Initially represents the current month's data; upon a next or previous request, it
 *   will be updated. It is observable;
 * @property currentMonthIndex Months are January to December, so the indices are 0-11 (total of 12). This helps the consumer
 *   know which month should be shown. Using this index, the consumer may find the current month's name or other
 *   info if needed. It is not observable; it is a primitive.
 * @property setYearData Data for all 12 months; a list of size 12 containing [MonthData].
 * @property goToNextMonth Updates the [currentMonthCalendar] with the next month's data (if available).
 * @property goToPreviousMonth Updates the [currentMonthCalendar] with the previous month's data (if available).
 *
 */

interface CalendarViewerController {
    val currentMonthCalendar: StateFlow<MonthData?>
    val currentMonthIndex:Int
    fun setYearData(yearData: List<MonthData>)
    fun goToNextMonth()
    fun goToPreviousMonth()
}
