package calendar.presentationlogic

import calendar.presentationlogic.model.HolidayType
import calendar.presentationlogic.model.MonthData
import feature.academiccalender.domain.model.AcademicCalender2
import feature.academiccalender.domain.model.Holiday

object ModelMapper {
    fun holidayModel(year: Int, yearData: List<MonthData>): AcademicCalender2 {

        val holidays: List<List<Holiday>> = yearData.mapIndexed { index, month ->
            month.cells.mapNotNull { cell ->
                //if has holiday on that day
                val day = cell.dayOfMonth
                val holiday = cell.holiday
                if (day != null && holiday != null && holiday.reason != "Weekend")//Need to add the weekend to database
                    Holiday(
                        day = day,
                        reason = holiday.reason,
                        holidayType = when (holiday.type) {
                            HolidayType.AllOff -> feature.academiccalender.domain.model.HolidayType.AllOff
                            HolidayType.SpecialDay -> feature.academiccalender.domain.model.HolidayType.SpecialDay
                            HolidayType.OnlyClassOf -> feature.academiccalender.domain.model.HolidayType.OnlyClassOff
                        }

                    )
                else null
            }
        }
        return AcademicCalender2(
            year = year,
            holiday = holidays
        )
    }
}