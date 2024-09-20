@file:Suppress("UnUsed", "LocalVariableName", "FunctionName")

package calender.interface_adapter

import calender.common.CalenderCellUiModel
import calender.common.DayName
import calender.common.HolidayUiModel
import domain.model.CalendarModel
import domain.model.DayModel
import domain.model.DayNameModel
import domain.model.HolidayType
import domain.model.MonthModel

/**
 * Builds a calendar grid for a given month.
 *
 * Why use a grid size of 35?
 * - The calendar grid consists of 7 columns (one for each day of the week: Saturday to Friday).
 * - Each row represents one full week. The grid can contain a maximum of 5 rows, which covers most months.
 * - 7 days/week × 5 weeks = 35 cells in total.
 *
 * Why use 7 columns?
 * - The 7 columns correspond to the 7 days of the week (Saturday, Sunday, Monday, ..., Friday).
 * - Each column represents one specific day of the week, ensuring that days are consistently aligned in the calendar grid.
 *
 * Why use 5 rows?
 * - A month has a maximum of 31 days. To represent these days in the grid, we typically need 5 rows.
 * - Even though some months only require 4 rows (e.g., February), months with 31 days and a late starting weekday (e.g., a month starting on Friday) require 5 rows.
 * - The 35 cells (7 columns × 5 rows) provide enough space to display any month, including months that need 5 full weeks (e.g., July).
 *
 * Why use % 35 in the grid placement logic?
 * - The modulo operation (`% 35`) ensures that the index is kept within the bounds of the grid.
 * - When placing days in the grid, we increment the `currentCellIndex` for each day.
 * - If, for any reason, the index exceeds 34 (the last index in the 35-cell grid), the modulo operation wraps the index back to the beginning of the grid.
 * - This is a safeguard to ensure that all cells are placed correctly and stay within the 35-cell grid limit.
 *
 * Example scenario for clarification:
 * - If a month starts on Friday and has 31 days, you would place the 1st day on the 6th cell (Friday column).
 * - By incrementing the `currentCellIndex` for each day, the 31st day will end up in the 35th cell (index 34).
 * - Using `currentCellIndex % 35` ensures that no index exceeds the available grid size and that any overflow is properly handled.
 */

internal class CalendarUIGridModelPresenter {
    fun buildMonthGrid(model: CalendarModel): List<List<CalenderCellUiModel>> {
        return buildMonthGrid(model.months)
    }

    private fun buildMonthGrid(data: List<MonthModel>): List<List<CalenderCellUiModel>> {
        val result = mutableListOf<List<CalenderCellUiModel>>()
        data.forEach {
            result.add(buildMonthGrid(it))
        }
        return result
    }

    private fun buildMonthGrid(monthData: MonthModel): List<CalenderCellUiModel> {
        val cells = createEmptyGrid()
        val firstDayCellNo = findFirstDayCell(monthData)
        placeDaysInGrid(cells, monthData, firstDayCellNo)
        return cells
    }

    private fun createEmptyGrid(): MutableList<CalenderCellUiModel> {
        return MutableList(35) { index ->
            CalenderCellUiModel(cellNo = index)
        }
    }

    /** Cell No for date=1 which is basically the Column no where Column=Day(ex: Sun,Mon...) ordinal in the Calender*/
    private fun findFirstDayCell(monthData: MonthModel): Int {
        return monthData.days.find { it.date == 1 }?.name?.ordinal
            ?: 0 // Default to 0 if not found (should not happen for a valid month)
    }


    private fun placeDaysInGrid(
        calenderCellUiModels: MutableList<CalenderCellUiModel>,
        months: MonthModel,
        firstDayCellNo: Int
    ) {
        var currentCellIndex = firstDayCellNo
        months
            .days
            .sortedBy { it.date }
            .forEach { day ->
                calenderCellUiModels[currentCellIndex % 35] =
                    day._toDayUiModel(currentCellIndex % 35)
                currentCellIndex++
            }
    }


    //TODO:Helper methods

    private fun DayModel._toDayUiModel(cellNo: Int): CalenderCellUiModel {
        val holiday = if (this.holiday != null)
            HolidayUiModel(
                reason = this.holiday!!.reason,
                colorHexCode = when (this.holiday!!.type) {
                    HolidayType.AllOff -> "#FF0000"       // Red color hex code for AllOff
                    HolidayType.OnlyClassOff -> "#00FF00" // Green color hex code for OnlyClassOff
                    HolidayType.SpecialDay -> "#800080"   // Purple color hex code for SpecialDay
                }

            )
        else null
        return CalenderCellUiModel(
            cellNo = cellNo,
            dayOrdinal = this.date,
            holiday = holiday,
            dayName = when (this.name) {
                DayNameModel.SATURDAY -> DayName.Sat
                DayNameModel.SUNDAY -> DayName.Sun
                DayNameModel.MONDAY -> DayName.Mon
                DayNameModel.TUESDAY -> DayName.Tue
                DayNameModel.WEDNESDAY -> DayName.Wed
                DayNameModel.THURSDAY -> DayName.Thu
                DayNameModel.FRIDAY -> DayName.Fri
            }
        )
    }
}


