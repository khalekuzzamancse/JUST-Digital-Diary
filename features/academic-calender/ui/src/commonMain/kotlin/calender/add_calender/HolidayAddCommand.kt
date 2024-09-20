@file:Suppress("VariableName", "FunctionName")

package calender.add_calender

import calender.common.CalendarCellUiModel
import calender.common.HolidayUiModel
import calender.interface_adapter.MonthDataUiModel

/**
 * - Encapsulate a single operation
 * - Using the concept of command pattern
 * - Undo is available
 */
class HolidayAddCommand(
    private var yearData: List<MonthDataUiModel>,
    private val dateOrdinals: List<Int>,
    private val reason: String,
    private val type: HolidayTypeUiModel,
    private val monthOrdinal: Int
) {

    private lateinit var currentMonthData: MonthDataUiModel

    /**
     * @return updated data for all month
     */
    fun execute(): List<MonthDataUiModel> {
        currentMonthData = yearData[monthOrdinal]
        val updatedCell = currentMonthData
            .cells
            .map { day ->
                val isSelected = (dateOrdinals.contains(day.dayOrdinal))
                if (isSelected)
                    day._updateHolidayAndReturn(reason, type)
                else
                    day

            }
        return yearData._updateDateOf(monthOrdinal, MonthDataUiModel(currentMonthData.name,updatedCell))
    }

    fun undo() = yearData

    //TODO: Helper method
    private fun List<MonthDataUiModel>._updateDateOf(
        index: Int,
        data: MonthDataUiModel
    ): List<MonthDataUiModel> {
        val newList = this.map { it }.toMutableList()
        newList[index] = data
        return newList
    }


    private fun CalendarCellUiModel._updateHolidayAndReturn(
        reason: String,
        type: HolidayTypeUiModel
    ) =
        this.copy(holiday = HolidayUiModel(colorHexCode = type.color, reason = reason))
}