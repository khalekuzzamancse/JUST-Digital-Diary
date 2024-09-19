package calender.ui.calender

import java.time.YearMonth

object CalenderFactory {
    private val calender = CalenderBuilder.currentYearCalender()
    val currentYear = YearMonth.now().year.toString()
    const val COLUMN_COUNT = 7
    private const val ROW_COUNT = 5
    const val FIRST_COLUMN = 0
    const val CELL_COUNT = ROW_COUNT * COLUMN_COUNT


    fun getCurrentMonthCalenderBuilder(): MonthCalenderBuilder {
        val currentMonthOrdinal = YearMonth.now().month.value - 1//converting 0 based
        return getCalenderBuilderOrThrow(currentMonthOrdinal)
    }

    fun getNextMonthCalenderBuilder(currentMonth: MonthName): MonthCalenderBuilder? {
        val nextMonthOrdinal = currentMonth.ordinal + 1 //0 based ordering
        return getCalenderBuilder(nextMonthOrdinal)

    }

    fun getPreviousMonthCalenderBuilder(currentMonth: MonthName): MonthCalenderBuilder? {
        val nextMonthOrdinal = currentMonth.ordinal - 1 //0 based ordering
        return getCalenderBuilder(nextMonthOrdinal)

    }

    private fun getCalenderBuilder(monthOrdinal: Int): MonthCalenderBuilder? {
        val builder = generateCalenderCells(monthOrdinal)
        return if (builder == null)
            null
        else {
            MonthCalenderBuilder(
                monthName = MonthName.entries[monthOrdinal]
            )
        }
    }

    private fun getCalenderBuilderOrThrow(monthOrdinal: Int): MonthCalenderBuilder {
        return MonthCalenderBuilder(
            monthName = MonthName.entries[monthOrdinal]
        )

    }


    fun dayName(columnNo: Int): String {
        return DayName.entries[columnNo].name.take(3).uppercase()
    }

    /**
     * @param [monthName]
     */
    class MonthCalenderBuilder(val monthName: MonthName) {
        private val calenderCells = generateCalenderCells(monthName)
        fun dateOnThisCell(cellNo: Int): Int? {
            val date = calenderCells.find { it.cellNoOnCalenderGrid == cellNo }
            return date?.date
        }
    }

    /**
     * * return in which cell which date will be
     */

    private fun generateCalenderCells(monthName: MonthName): List<CalenderCell> {
        val totalCells = 35//7 days *5 ,at most 31 will be used.
        val month = calender.months[monthName.order]
        val cellNoOf1stDate = month.dates.first().dayName.ordinal
        var currentCellNo = cellNoOf1stDate
        return month.dates.map { dayOfMonth ->
            CalenderCell(
                date = dayOfMonth.date,
                cellNoOnCalenderGrid = (currentCellNo++) % totalCells
            )
        }.sortedBy { it.cellNoOnCalenderGrid }
    }

    private fun generateCalenderCells(monOrdinal: Int): List<CalenderCell>? {
        val totalCells = 35//7 days *5 ,at most 31 will be used.
        val monthNotExit = monOrdinal < 0 || monOrdinal > 11
        if (monthNotExit) return null

        val month = calender.months[monOrdinal]
        val cellNoOf1stDate = month.dates.first().dayName.ordinal
        var currentCellNo = cellNoOf1stDate
        return month.dates.map { dayOfMonth ->
            CalenderCell(
                date = dayOfMonth.date,
                cellNoOnCalenderGrid = (currentCellNo++) % totalCells
            )
        }.sortedBy { it.cellNoOnCalenderGrid }
    }

}
data class CalenderCell(
    val date: Int,
    val cellNoOnCalenderGrid: Int
)