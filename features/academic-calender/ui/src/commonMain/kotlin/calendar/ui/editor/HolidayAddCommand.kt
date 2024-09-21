@file:Suppress("VariableName", "FunctionName","UnUsed")

package calendar.ui.editor

import calendar.ui.common.model.CalendarGridCell
import calendar.ui.common.model.Holiday
import calendar.ui.common.model.HolidayType
import calendar.ui.common.model.MonthData

/**
 * - Encapsulates a single operation for adding holidays to selected dates.
 *   Instead of implementing this operation as a function in the `EditorController`, we encapsulate it into a command.
 *   This reduces the responsibility and code length of `EditorController`.
 *   Using the concept of the Command Pattern, this allows for easy implementation of undo functionality.
 *   - Undo is available.
 *
 * @property yearData A mutable list (`List<MonthDataUiModel>`) representing data for all months of the year.
 * @property dateOrdinals A list of integers (`List<Int>`) representing the selected dates (days of the month) to which holidays will be added.
 * @property reason A `String` representing the reason for the holiday.
 * @property type A `HolidayTypeUiModel` representing the type of the holiday, which includes properties like color code.
 * @property monthOrdinal An `Int` representing the index of the month (0-11) in which the holidays are to be added.
 *
 */
class HolidayAddCommand(
    private var yearData: List<MonthData>,
    private val dateOrdinals: List<Int>,
    private val reason: String,
    private val type: HolidayType,
    private val monthOrdinal: Int
) {

    private lateinit var currentMonthData: MonthData

    /**
     * Executes the command to add holidays to the selected dates.
     *
     * @return An updated list (`List<MonthDataUiModel>`) containing the modified month data with the holidays added.
     */
    fun execute(): List<MonthData> {
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

    /**
     * Undoes the operation, returning the original year data.
     *
     * @return The original list (`List<MonthDataUiModel>`) before the holidays were added.
     */
    fun undo(): List<MonthData> = yearData

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
        this.copy(holiday = Holiday(colorCode = type.color, reason = reason))
}
