@file:Suppress("UnUsed")
package data.repository

import component.ApiServiceClient
import component.JsonParser
import domain.exception.CalendarFeatureException
import domain.model.AcademicCalendar
import domain.repository.CalenderRepository

class CalenderRepositoryImpl(
    val apiServiceClient:ApiServiceClient,
    val jsonParser: JsonParser
): CalenderRepository {
    override fun addCalender(calender: AcademicCalendar): CalendarFeatureException? {
        TODO("Not yet implemented")
    }

    override fun retrieveCalender(year: Int): Result<AcademicCalendar> {
        TODO("Not yet implemented")
    }
}