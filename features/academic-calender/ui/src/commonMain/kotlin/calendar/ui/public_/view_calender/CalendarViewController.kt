package calendar.ui.public_.view_calender

import calendar.presentationlogic.model.MonthData
import kotlinx.coroutines.flow.StateFlow

/**
 * - Manage the state and event of the [AcademicCalender]
 * - ViewModel can implement it(optional)
 */
interface CalendarViewController {
    val currentMonthCalender: StateFlow<MonthData?>
    val year: StateFlow<Int?>

    /**Update the [currentMonthCalender] with next month*/
    fun goToNextMonthCalender()

    /**Update the [currentMonthCalender] with previous  month*/
    fun goToPreviousMonthCalender()
}
