@file:Suppress("UnUsed")

package data.misc

import feature.academiccalender.domain.model.CalendarModel
import feature.academiccalender.domain.model.DayModel
import feature.academiccalender.domain.model.HolidayModel
import feature.academiccalender.domain.model.HolidayType
import feature.academiccalender.domain.model.MonthModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month

class CalendarBuilder {
    private val weekends: MutableList<feature.academiccalender.domain.model.DayOfWeek> = mutableListOf()

    fun addWeekend(day: feature.academiccalender.domain.model.DayOfWeek): CalendarBuilder {
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
                HolidayType.AllOff,
                "Weekend"
            ) else null
            DayModel(dayName, dayOfMonth, holiday)
        }
        return MonthModel(month, days)
    }

    private fun mapDayOfWeek(dayOfWeek: DayOfWeek): feature.academiccalender.domain.model.DayOfWeek {
        return when (dayOfWeek) {
            DayOfWeek.SATURDAY -> feature.academiccalender.domain.model.DayOfWeek.SATURDAY
            DayOfWeek.SUNDAY -> feature.academiccalender.domain.model.DayOfWeek.SUNDAY
            DayOfWeek.MONDAY -> feature.academiccalender.domain.model.DayOfWeek.MONDAY
            DayOfWeek.TUESDAY -> feature.academiccalender.domain.model.DayOfWeek.TUESDAY
            DayOfWeek.WEDNESDAY -> feature.academiccalender.domain.model.DayOfWeek.WEDNESDAY
            DayOfWeek.THURSDAY -> feature.academiccalender.domain.model.DayOfWeek.THURSDAY
            DayOfWeek.FRIDAY -> feature.academiccalender.domain.model.DayOfWeek.FRIDAY
        }
    }

    private fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }
}
