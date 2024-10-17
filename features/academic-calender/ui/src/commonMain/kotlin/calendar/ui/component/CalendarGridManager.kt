package calendar.ui.component

import calendar.presentationlogic.model.MonthData
import feature.academiccalender.domain.model.CalendarModel

internal interface CalendarGridManager {
    /**
     * Builds a month grid based on the given calendar model.
     *
     * @param model The calendar model containing the data to be used for building the grid.
     * @return A list of `MonthData` representing the grid structure for the calendar.
     */
    fun buildMonthGrid(model: CalendarModel): List<MonthData>
}