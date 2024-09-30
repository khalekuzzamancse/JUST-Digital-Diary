package calendar.ui.presenter

import calendar.ui.model.MonthData
import domain.model.CalendarModel

internal interface CalendarGridPresenter {
    /**
     * Builds a month grid based on the given calendar model.
     *
     * @param model The calendar model containing the data to be used for building the grid.
     * @return A list of `MonthData` representing the grid structure for the calendar.
     */
    fun buildMonthGrid(model: CalendarModel): List<MonthData>
}