package data.service

import feature.academiccalender.domain.exception.CustomException
import feature.academiccalender.domain.model.AcademicCalendar
import feature.academiccalender.domain.model.User
import feature.academiccalender.domain.service.CalendarService

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