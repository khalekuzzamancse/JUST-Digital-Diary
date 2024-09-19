@file:Suppress("UnUsed")

package data.schema

import kotlinx.serialization.Serializable

@Serializable
internal data class AcademicCalendar(
    val year: Int,
    val januaryHolidays: List<Holiday>,
    val februaryHolidays: List<Holiday>,
    val marchHolidays: List<Holiday>,
    val aprilHolidays: List<Holiday>,
    val mayHolidays: List<Holiday>,
    val juneHolidays: List<Holiday>,
    val julyHolidays: List<Holiday>,
    val augustHolidays: List<Holiday>,
    val septemberHolidays: List<Holiday>,
    val octoberHolidays: List<Holiday>,
    val novemberHolidays: List<Holiday>,
    val decemberHolidays: List<Holiday>
)

