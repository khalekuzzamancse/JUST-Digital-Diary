@file:Suppress("VariableName", "FunctionName")

package calender.add_calender

import calender.common.CalenderCellUiModel
import calender.common.HolidayUiModel
import java.time.Month

/**
 * - Encapsulate a single operation
 * - Using the concept of command pattern
 * - Undo is available
 */
class HolidayAddCommand(
    private var allMonthData: List<List<CalenderCellUiModel>>,
    private val dateOrdinals: List<Int>,
    private val reason: String,
    private val type: HolidayTypeUiModel,
    private val monthOrdinal: Int
) {

    private var currentMonthData: List<CalenderCellUiModel> = emptyList()

    /**
     * @return updated data for all month
     */
    fun execute(): List<List<CalenderCellUiModel>> {
        currentMonthData = allMonthData._dataOf(monthOrdinal)
        val currentMonthUpdatedData = currentMonthData
            .map { day ->
                val isSelected = (dateOrdinals.contains(day.dayOrdinal))
                if (isSelected)
                    day._updateHolidayAndReturn(reason, type)
                else
                    day

            }
        return allMonthData._updateDateOf(monthOrdinal, currentMonthUpdatedData)
    }

    fun undo() = allMonthData

    //TODO: Helper method
    private fun List<List<CalenderCellUiModel>>._updateDateOf(
        index: Int,
        data: List<CalenderCellUiModel>
    ): List<List<CalenderCellUiModel>> {
        val newList = this.map { it }.toMutableList()
        newList[index] = data
        return newList.toList()
    }

    private fun List<List<CalenderCellUiModel>>._dataOf(index: Int): List<CalenderCellUiModel> {
        return this[index]
    }

    private fun CalenderCellUiModel._updateHolidayAndReturn(
        reason: String,
        type: HolidayTypeUiModel
    ) =
        this.copy(holiday = HolidayUiModel(colorHexCode = type.color, reason = reason))
}