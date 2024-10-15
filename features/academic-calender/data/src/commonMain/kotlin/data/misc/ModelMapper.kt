@file:Suppress("UnUsed")

package data.misc

import data.entity.AcademicCalendarSchema
import data.entity.AcademicCalenderEntity
import data.entity.CalendarWrapperSchema
import data.entity.HolidayEntity
import data.entity.HolidaySchema
import data.entity.HolidayTypeSchema
import feature.academiccalender.domain.model.AcademicCalendar
import feature.academiccalender.domain.model.AcademicCalender2
import feature.academiccalender.domain.model.CalendarModel
import feature.academiccalender.domain.model.DayModel
import feature.academiccalender.domain.model.Holiday
import feature.academiccalender.domain.model.HolidayModel
import feature.academiccalender.domain.model.HolidayType

internal object ModelMapper {

    fun AcademicCalenderEntity.toModel(): CalendarModel {
        val rawCalendar =
            CalendarBuilder().build(this.year)//Building the raw calendar  without any holidays
        return HodidayAdder().add(rawCalendar, this)
    }
    fun AcademicCalender2.toEntity(): AcademicCalenderEntity {
        return AcademicCalenderEntity(
            year = this.year,
            holidays = this.holiday.map { monthHolidays ->
                monthHolidays.map { holiday ->
                    HolidayEntity(
                        day = holiday.day,
                        type = holiday.holidayType.toEntityType(),
                        reason = holiday.reason
                    )
                }
            }
        )
    }

    /**
     * Extension function to map HolidayType to its corresponding integer type for HolidayEntity.
     */
    private fun HolidayType.toEntityType(): Int {
        return when (this) {
            HolidayType.AllOff -> HolidayEntity.ALL_OFF
            HolidayType.OnlyClassOff -> HolidayEntity.ONLY_CLASS_OFF
            HolidayType.SpecialDay -> HolidayEntity.SPECIAL_DAY
        }
    }

    private fun DayModel._addHoidayIfAny(monthHolidays: List<HolidayEntity>): DayModel {
        //if this day is  a holiday
        val holiday = monthHolidays.find { it.day == this.date }
        return if (holiday == null)
            this
        else {
            val model = holiday._toHolidayModel()
            this.copy(
                holiday = model
            )

        }

    }

    private fun HolidayEntity._toHolidayModel(): HolidayModel? {
        val type = when (type) {
            HolidayEntity.ALL_OFF -> HolidayType.AllOff
            HolidayEntity.ONLY_CLASS_OFF -> HolidayType.OnlyClassOff
            HolidayEntity.SPECIAL_DAY -> HolidayType.SpecialDay
            else -> null//Not happen generally, if admin not mistake while inserting
        }

        return if (type != null)
            HolidayModel(
                reason = this.reason,
                type = type
            )
        else null

    }


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