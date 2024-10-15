@file:Suppress("VariableName", "FunctionName","UnUsed")

package calendar.presentationlogic.factory.add_calender

import calendar.presentationlogic.model.CalendarGridCell
import calendar.presentationlogic.model.Holiday
import calendar.presentationlogic.model.HolidayType
import calendar.presentationlogic.model.MonthData

/**
 * - Though it has no dependencies, should not create instances of it via the constructor; instead,
 * use a factory method or dependency injection (DI) to obtain an instance
 * - See [AddHolidayCommand] docs
 */
internal class AddHolidayCommandImpl : AddHolidayCommand {
    private lateinit var currentMonthData: MonthData
    private lateinit var yearData: List<MonthData>

    override fun execute(
        yearData: List<MonthData>,
        dateOrdinals: List<Int>,
        reason: String,
        type: HolidayType,
        monthOrdinal: Int
    ): List<MonthData> {
        this.yearData = yearData
        currentMonthData = yearData[monthOrdinal]

        val updatedCells = currentMonthData
            .cells
            .map { day ->
                if (dateOrdinals.contains(day.dayOfMonth)) {
                    day._updateHolidayAndReturn(reason, type)
                } else {
                    day
                }
            }

        return yearData._updateDateOf(monthOrdinal, MonthData(currentMonthData.name, updatedCells))
    }


    override fun undo(): List<MonthData> = yearData

    // Helper method to update the month data at a specific index.
    private fun List<MonthData>._updateDateOf(
        index: Int,
        data: MonthData
    ): List<MonthData> {
        val newList = this.toMutableList()
        newList[index] = data
        return newList
    }

    // Helper method to update a calendar cell with holiday information.
    private fun CalendarGridCell._updateHolidayAndReturn(
        reason: String,
        type: HolidayType
    ): CalendarGridCell =
        this.copy(holiday = Holiday(reason = reason,type = type))
}