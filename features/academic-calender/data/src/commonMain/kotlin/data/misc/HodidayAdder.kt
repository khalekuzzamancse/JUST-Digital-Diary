@file:Suppress("unused")
package data.misc

import data.entity.AcademicCalenderEntity
import data.entity.HolidayEntity
import feature.academiccalender.domain.model.CalendarModel
import feature.academiccalender.domain.model.DayModel
import feature.academiccalender.domain.model.HolidayModel
import feature.academiccalender.domain.model.HolidayType
import feature.academiccalender.domain.model.MonthModel

class HodidayAdder {

    /**
     * Updates the CalendarModel based on the holidays from AcademicCalenderEntity.
     * It maps holidays from AcademicCalenderEntity to the appropriate month and days in the CalendarModel.
     */
    fun add(
        calendarModel: CalendarModel,
        academicCalendar: AcademicCalenderEntity
    ): CalendarModel {
        // Map each month in CalendarModel with its corresponding holidays from AcademicCalenderEntity
        val updatedMonths = calendarModel.months.mapIndexed { index, monthModel ->
            updateMonthModel(monthModel, academicCalendar.holidays.getOrNull(index) ?: emptyList())
        }

        return calendarModel.copy(months = updatedMonths)
    }

    /**
     * Updates the MonthModel by checking for holidays in the corresponding month from AcademicCalenderEntity.
     */
    private fun updateMonthModel(
        monthModel: MonthModel,
        monthHolidays: List<HolidayEntity>
    ): MonthModel {
        // Update each day in the month based on the holiday data from AcademicCalenderEntity
        val updatedDays = monthModel.days.map { dayModel ->
            updateDayModel(dayModel, monthHolidays)
        }
        return monthModel.copy(days = updatedDays)
    }

    /**
     * Updates the DayModel by checking if there is a holiday for that day in the AcademicCalenderEntity.
     * If no holiday type is found, the day is returned as is.
     */
    private fun updateDayModel(
        dayModel: DayModel,
        monthHolidays: List<HolidayEntity>
    ): DayModel {
        val holidayForDay = monthHolidays.find { it.day == dayModel.date }
        return if (holidayForDay != null) {
            // Map the holiday type and ensure it's valid
            val holidayType = mapHolidayType(holidayForDay.type)
            if (holidayType != null) {
                // If a valid holiday type is found, update the DayModel with HolidayModel
                dayModel.copy(
                    holiday = HolidayModel(
                        type = holidayType,
                        reason = holidayForDay.reason
                    )
                )
            } else {
                // If the holiday type is null, return the day as is (skip update)
                dayModel
            }
        } else {
            // If no holiday, return the day as it is
            dayModel
        }
    }

    /**
     * Maps the holiday type from AcademicCalenderEntity to CalendarModel's HolidayType.
     * Returns null if no valid holiday type is found.
     */
    private fun mapHolidayType(holidayType: Int): HolidayType? {
        return when (holidayType) {
            HolidayEntity.ALL_OFF -> HolidayType.AllOff
            HolidayEntity.ONLY_CLASS_OFF -> HolidayType.OnlyClassOff
            HolidayEntity.SPECIAL_DAY -> HolidayType.SpecialDay
            else -> null // Return null if the holiday type doesn't match
        }
    }
}
