@file:Suppress("UnUsed")

package data.misc

import domain.model.CalendarModel
import domain.model.DayModel
import domain.model.DayNameModel
import domain.model.HolidayModel
import domain.model.HolidayType
import domain.model.MonthModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month

class CalendarBuilder {
    private val weekends: MutableList<DayNameModel> = mutableListOf()

    fun addWeekend(day: DayNameModel): CalendarBuilder {
        weekends.add(day)
        return this
    }

    fun build(year: Int): CalendarModel {
        val months = Month.values().map { month ->
            buildMonth(year, month)
        }
        return CalendarModel(year, months)
    }

    private fun buildMonth(year: Int, month: Month): MonthModel {
        val daysInMonth = month.length(isLeapYear(year))
        val days = (1..daysInMonth).map { dayOfMonth ->
            val date = LocalDate.of(year, month, dayOfMonth)
            val dayName = mapDayOfWeek(date.dayOfWeek)
            // Check if the day is a weekend and mark as holiday if it is
            val holiday = if (weekends.contains(dayName)) HolidayModel(
                HolidayType.Weekend,
                "Weekend"
            ) else null
            DayModel(dayName, dayOfMonth, holiday)
        }
        return MonthModel(month, days)
    }

    private fun mapDayOfWeek(dayOfWeek: DayOfWeek): DayNameModel {
        return when (dayOfWeek) {
            DayOfWeek.SATURDAY -> DayNameModel.SATURDAY
            DayOfWeek.SUNDAY -> DayNameModel.SUNDAY
            DayOfWeek.MONDAY -> DayNameModel.MONDAY
            DayOfWeek.TUESDAY -> DayNameModel.TUESDAY
            DayOfWeek.WEDNESDAY -> DayNameModel.WEDNESDAY
            DayOfWeek.THURSDAY -> DayNameModel.THURSDAY
            DayOfWeek.FRIDAY -> DayNameModel.FRIDAY
        }
    }

    private fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }
}
