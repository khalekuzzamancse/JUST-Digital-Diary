@file:Suppress("UnUsed")

package domain.service

import common.docs.domain_layer.ServiceDoc
import domain.exception.CustomException
import domain.model.AcademicCalendar
import domain.model.User
import common.docs.domain_layer.CustomExceptionDoc
/**
 *  Further discussion on:
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
interface CalendarService {

    /**
     * Validates the content of the [AcademicCalendar] before it is added or updated in the repository
     * @param calendar The [AcademicCalendar] to be validated
     */

    fun validateCalender(calendar: AcademicCalendar): CustomException?

    /**
     * - Validates user has right permission or not for adding/updating or fetching the calender to database
     */
    fun validateAuthenticity(user: User): CustomException?
    /**
     * - Validates user has right permission or not for adding/updating or fetching the calender to database
     */
     fun validateYear(year: Int):  CustomException?

}
