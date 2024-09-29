package data.entity

import kotlinx.serialization.Serializable

@Serializable
internal data class CalendarWrapperSchema(
    val academicCalendar: AcademicCalendarSchema
)