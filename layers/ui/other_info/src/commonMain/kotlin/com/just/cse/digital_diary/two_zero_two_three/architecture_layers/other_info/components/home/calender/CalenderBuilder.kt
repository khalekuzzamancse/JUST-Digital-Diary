package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.calender

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

/**
 * * Its okay to use a singleton because the calender are globally same
 */
object CalenderBuilder {
    /**
     * * This a large function refactor is small small chunks
     */
    fun currentYearCalender(): CurrentYearCalender {
        val currentYear = YearMonth.now().year
        val allMonthsData = mutableListOf<List<DateOfMonth>>()
        for (month in 1..12) {
            val firstDayOfMonth = LocalDate.of(currentYear, month, 1)
            val lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1)

            val dateOfMonthList = mutableListOf<DateOfMonth>()

            var currentDate = firstDayOfMonth
            while (currentDate.isBefore(lastDayOfMonth) || currentDate.isEqual(lastDayOfMonth)) {
                val formattedDate = currentDate.dayOfMonth
                val dayName = currentDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                val dateOfMonth = DateOfMonth(
                    formattedDate,
                    dayName.toDayName()
                )
                dateOfMonthList.add(dateOfMonth)
                currentDate = currentDate.plusDays(1)
            }

            allMonthsData.add(dateOfMonthList)
        }

        // Access the list for further use
        val months = mutableListOf<MonthOfYear>()
        for ((index, monthData) in allMonthsData.withIndex()) {
            val monthName =
                java.time.Month.of(index + 1).getDisplayName(TextStyle.FULL, Locale.ENGLISH)
            months.add(MonthOfYear(monthName, monthData))
        }
        return CurrentYearCalender(
            name = currentYear.toString(),
            months = months
        )

    }

    private fun String.toDayName(): DayName {
        return when (this.lowercase(Locale.getDefault())) {
            "saturday" -> DayName.Saturday
            "sunday" -> DayName.Sunday
            "monday" -> DayName.Monday
            "tuesday" -> DayName.Tuesday
            "wednesday" -> DayName.Wednesday
            "thursday" -> DayName.Thursday
            "friday" -> DayName.Friday
            else -> throw IllegalArgumentException("Invalid dayName: $this")
        }
    }

}
