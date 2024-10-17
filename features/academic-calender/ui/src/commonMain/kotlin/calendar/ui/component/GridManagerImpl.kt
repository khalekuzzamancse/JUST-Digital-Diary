@file:Suppress("UnUsed", "LocalVariableName", "FunctionName")

package calendar.ui.component

import calendar.presentationlogic.model.CalendarGridCell
import calendar.presentationlogic.model.Holiday
import calendar.presentationlogic.model.MonthData
import calendar.presentationlogic.model.DayOfWeek
import calendar.presentationlogic.model.HolidayType
import feature.academiccalender.domain.model.CalendarModel
import feature.academiccalender.domain.model.DayModel
import feature.academiccalender.domain.model.MonthModel

/**
 * - Take data format defined as in `domain` layer and `convert` to `ui` layer format
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

internal class GridManagerImpl: CalendarGridManager {
    override fun buildMonthGrid(model: CalendarModel): List<MonthData> {
        return _buildMonthGrid(model.months)
    }

    private fun _buildMonthGrid(data: List<MonthModel>): List<MonthData> {
        val result = mutableListOf<MonthData>()
        data.forEach {
            result.add(_buildMonthGrid(it))
        }
        return result
    }

    private fun _buildMonthGrid(monthData: MonthModel): MonthData {
        val cells = _createEmptyGrid()
        val firstDayCellNo = _findFirstDayCell(monthData)
        _placeDaysInGrid(cells, monthData, firstDayCellNo)
        return MonthData(monthData.month.name, cells)
    }

    private fun _createEmptyGrid(): MutableList<CalendarGridCell> {
        return MutableList(35) { index ->
            CalendarGridCell(cellIndex = index)
        }
    }

    /** Cell No for date=1 which is basically the Column no where Column=Day(ex: Sun,Mon...) ordinal in the Calender*/
    private fun _findFirstDayCell(monthData: MonthModel): Int {
        return monthData.days.find { it.date == 1 }?.name?.ordinal
            ?: 0 // Default to 0 if not found (should not happen for a valid month)
    }


    private fun _placeDaysInGrid(
        calenderCellUiModels: MutableList<CalendarGridCell>,
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

    private fun DayModel._toDayUiModel(cellNo: Int): CalendarGridCell {
        val holiday = if (this.holiday != null)
            Holiday(
                reason = this.holiday!!.reason,
                type = _toUiHolidayType(this.holiday!!.type)
            )
        else null
        return CalendarGridCell(
            cellIndex = cellNo,
            dayOfMonth = this.date,
            holiday = holiday,
            dayOfWeek = _toUiDayOfWeek(this.name)
        )
    }

    /**
     * Maps a `domain.model.DayOfWeek` to a `calendar.common.model.DayOfWeek`.
     *
     * @param day The day of the week from the domain model.
     * @return The corresponding day of the week in the calendar common model.
     */
    private fun _toUiDayOfWeek(day: feature.academiccalender.domain.model.DayOfWeek): DayOfWeek {
        return when (day) {
            feature.academiccalender.domain.model.DayOfWeek.SATURDAY -> DayOfWeek.Sat
            feature.academiccalender.domain.model.DayOfWeek.SUNDAY -> DayOfWeek.Sun
            feature.academiccalender.domain.model.DayOfWeek.MONDAY -> DayOfWeek.Mon
            feature.academiccalender.domain.model.DayOfWeek.TUESDAY -> DayOfWeek.Tue
            feature.academiccalender.domain.model.DayOfWeek.WEDNESDAY -> DayOfWeek.Wed
            feature.academiccalender.domain.model.DayOfWeek.THURSDAY -> DayOfWeek.Thu
            feature.academiccalender.domain.model.DayOfWeek.FRIDAY -> DayOfWeek.Fri
        }
    }

    private fun _toUiHolidayType(type: feature.academiccalender.domain.model.HolidayType): HolidayType {
        return when (type) {
            feature.academiccalender.domain.model.HolidayType.AllOff -> HolidayType.AllOff
            feature.academiccalender.domain.model.HolidayType.OnlyClassOff -> HolidayType.OnlyClassOf
            feature.academiccalender.domain.model.HolidayType.SpecialDay -> HolidayType.SpecialDay
        }
    }
}

