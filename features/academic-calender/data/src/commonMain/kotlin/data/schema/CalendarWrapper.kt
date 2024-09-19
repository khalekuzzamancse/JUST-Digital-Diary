package data.schema

import kotlinx.serialization.Serializable

@Serializable
internal data class CalendarWrapper(
    val academicCalendar: AcademicCalendar
)