package data.service

import domain.exception.CalendarFeatureException
import domain.model.AcademicCalendar
import domain.model.User
import domain.service.CalendarService

class CalendarServiceImpl: CalendarService {
    override fun validateCalender(calendar: AcademicCalendar): CalendarFeatureException? {
        TODO("Not yet implemented")
    }

    override fun validateAuthenticity(user: User): CalendarFeatureException? {
        TODO("Not yet implemented")
    }

    override fun validateYear(year: Int): CalendarFeatureException? {
        TODO("Not yet implemented")
    }
}