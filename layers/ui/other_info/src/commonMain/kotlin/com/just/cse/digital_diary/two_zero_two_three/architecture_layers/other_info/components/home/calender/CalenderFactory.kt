package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.calender

import java.time.YearMonth

object CalenderFactory {
    private val calender = CalenderBuilder.currentYearCalender()
    val currentYear = YearMonth.now().year.toString()
    const val COLUMN_COUNT = 7
    const val ROW_COUNT = 5
    const val FIRST_COLUMN=0
    const  val CELL_COUNT= ROW_COUNT* COLUMN_COUNT
    fun dayName(columnNo:Int):String{
        return DayName.entries[columnNo].name.take(3).uppercase()
    }
    class MonthCalenderBuilder(monthName: MonthName) {
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

}
