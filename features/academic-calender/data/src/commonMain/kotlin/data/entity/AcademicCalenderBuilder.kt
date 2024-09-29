@file:Suppress("UnUsed")

package data.entity

import kotlinx.serialization.Serializable

@Serializable
internal data class AcademicCalendarSchema(
    val year: Int,
    val januaryHolidays: List<HolidaySchema>,
    val februaryHolidays: List<HolidaySchema>,
    val marchHolidays: List<HolidaySchema>,
    val aprilHolidays: List<HolidaySchema>,
    val mayHolidays: List<HolidaySchema>,
    val juneHolidays: List<HolidaySchema>,
    val julyHolidays: List<HolidaySchema>,
    val augustHolidays: List<HolidaySchema>,
    val septemberHolidays: List<HolidaySchema>,
    val octoberHolidays: List<HolidaySchema>,
    val novemberHolidays: List<HolidaySchema>,
    val decemberHolidays: List<HolidaySchema>
)

