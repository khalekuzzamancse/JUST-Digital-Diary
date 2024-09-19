@file:Suppress("UnUsed")
package data.misc

import data.schema.AcademicCalendarSchema
import data.schema.CalendarWrapperSchema
import data.schema.HolidaySchema
import data.schema.HolidayTypeSchema
import domain.model.AcademicCalendar
import domain.model.Holiday
import domain.model.HolidayType

internal object SchemaToModelConverter {
    // Converts HolidayTypeSchema to HolidayType
    fun HolidayTypeSchema.toModel(): HolidayType {
        return when (this) {
            HolidayTypeSchema.OnlyClassOff -> HolidayType.OnlyClassOff
            HolidayTypeSchema.AllOff -> HolidayType.AllOff
            HolidayTypeSchema.OnlyOfficeOff -> HolidayType.OnlyOfficeOff
            HolidayTypeSchema.SpecialDay -> HolidayType.SpecialDay
        }
    }

    // Converts HolidaySchema to Holiday (model)
    fun HolidaySchema.toModel(): Holiday {
        return Holiday(
            day = this.day,
            holidayType = this.holidayType.toModel(),
            reason = this.reason
        )
    }

    // Converts AcademicCalendarSchema to AcademicCalendar (model)
    fun AcademicCalendarSchema.toModel(): AcademicCalendar {
        return AcademicCalendar.Builder(year)
            .januaryHolidays(januaryHolidays.map { it.toModel() })
            .februaryHolidays(februaryHolidays.map { it.toModel() })
            .marchHolidays(marchHolidays.map { it.toModel() })
            .aprilHolidays(aprilHolidays.map { it.toModel() })
            .mayHolidays(mayHolidays.map { it.toModel() })
            .juneHolidays(juneHolidays.map { it.toModel() })
            .julyHolidays(julyHolidays.map { it.toModel() })
            .augustHolidays(augustHolidays.map { it.toModel() })
            .septemberHolidays(septemberHolidays.map { it.toModel() })
            .octoberHolidays(octoberHolidays.map { it.toModel() })
            .novemberHolidays(novemberHolidays.map { it.toModel() })
            .decemberHolidays(decemberHolidays.map { it.toModel() })
            .build()
    }

    // Converts CalendarWrapperSchema to CalendarWrapper (model)
    fun CalendarWrapperSchema.toModel(): AcademicCalendar {
        return this.academicCalendar.toModel()
    }

}