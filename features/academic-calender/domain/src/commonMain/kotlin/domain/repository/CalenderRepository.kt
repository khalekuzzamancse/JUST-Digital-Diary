@file:Suppress("UnUsed")
package domain.repository
import domain.docs.CustomExceptionDoc
import domain.docs.RepositoryDoc

import domain.exception.CalendarFeatureException
import domain.model.AcademicCalendar

/**
 * Further discussion on:
 *  - `Repository`: see [RepositoryDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */

interface CalenderRepository {
    /**
     * Adds a calendar to the repository
     *
     *  - On failure, return [CalendarFeatureException] instead of throwing generic exceptions to  ensures concrete
     *  classes only throw exceptions defined in the 'domain' module
     * @return onSuccess, it return null
     */
    fun addCalender(calender:AcademicCalendar): CalendarFeatureException?

    /**
     * Fetches the calendar for the specified year from the database or API
     *
     *  - On failure, return [CalendarFeatureException] instead of throwing generic exceptions to  ensures concrete
     *  classes only throw exceptions defined in the 'domain' module
     * @param year The year for which the calendar is being retrieved
     * @return [Result] wrapping either the [AcademicCalendar] on success, or a [CalendarFeatureException] on failure
     */
    fun retrieveCalender(year: Int): Result<AcademicCalendar>
}