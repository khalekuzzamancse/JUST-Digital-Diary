@file:Suppress("UnUsed")
package data.misc
import data.entity.AcademicCalendarSchema
import data.entity.CalendarWrapperSchema
import data.entity.HolidaySchema
import data.entity.HolidayTypeSchema
import domain.model.AcademicCalendar
import domain.model.Holiday
import domain.model.HolidayType

internal object ModelToSchema {
    // Converts HolidayType to HolidayTypeSchema
    fun HolidayType.toSchema(): HolidayTypeSchema {
        return when (this) {
            HolidayType.OnlyClassOff -> HolidayTypeSchema.OnlyClassOff
            HolidayType.AllOff -> HolidayTypeSchema.AllOff
            HolidayType.SpecialDay -> HolidayTypeSchema.SpecialDay
        }
    }

    // Converts Holiday (model) to HolidaySchema
    fun Holiday.toSchema(): HolidaySchema {
        return HolidaySchema(
            day = this.day,
            holidayType = this.holidayType.toSchema(),
            reason = this.reason
        )
    }

    // Converts AcademicCalendar (model) to AcademicCalendarSchema
    fun AcademicCalendar.toSchema(): AcademicCalendarSchema {
        return AcademicCalendarSchema(
            year = this.year,
            januaryHolidays = this.januaryHolidays.map { it.toSchema() },
            februaryHolidays = this.februaryHolidays.map { it.toSchema() },
            marchHolidays = this.marchHolidays.map { it.toSchema() },
            aprilHolidays = this.aprilHolidays.map { it.toSchema() },
            mayHolidays = this.mayHolidays.map { it.toSchema() },
            juneHolidays = this.juneHolidays.map { it.toSchema() },
            julyHolidays = this.julyHolidays.map { it.toSchema() },
            augustHolidays = this.augustHolidays.map { it.toSchema() },
            septemberHolidays = this.septemberHolidays.map { it.toSchema() },
            octoberHolidays = this.octoberHolidays.map { it.toSchema() },
            novemberHolidays = this.novemberHolidays.map { it.toSchema() },
            decemberHolidays = this.decemberHolidays.map { it.toSchema() }
        )
    }

    // Converts CalendarWrapper (model) to CalendarWrapperSchema
    fun AcademicCalendar.toSchemaWrapper(): CalendarWrapperSchema {
        return CalendarWrapperSchema(
            academicCalendar = this.toSchema()
        )
    }

}