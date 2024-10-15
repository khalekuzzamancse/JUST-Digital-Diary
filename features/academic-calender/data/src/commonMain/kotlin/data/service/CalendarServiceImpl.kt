package data.service

import domain.exception.CustomException
import domain.model.AcademicCalendar
import domain.model.User
import domain.service.CalendarService

class CalendarServiceImpl: CalendarService {
    override fun validateCalender(calendar: AcademicCalendar): CustomException? {
        TODO("Not yet implemented")
    }

    override fun validateAuthenticity(user: User): CustomException? {
        TODO("Not yet implemented")
    }

    override fun validateYear(year: Int): CustomException? {
        TODO("Not yet implemented")
    }
}