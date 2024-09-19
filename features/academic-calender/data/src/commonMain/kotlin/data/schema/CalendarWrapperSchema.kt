package data.schema

import kotlinx.serialization.Serializable

@Serializable
internal data class CalendarWrapperSchema(
    val academicCalendar: AcademicCalendarSchema
)