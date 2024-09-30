@file:Suppress("UnUsed")
package domain.repository
import common.docs.domain_layer.CustomExceptionDoc
import common.docs.domain_layer.RepositoryDoc



import domain.exception.CalendarFeatureException
import domain.model.AcademicCalendar
import domain.model.CalendarModel
import domain.model.DayOfWeek

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
     * @return [Result] wrapping either the [CalendarModel] on success, or a [CalendarFeatureException] on failure
     */
  suspend  fun retrieveAcademicCalender(year: Int): Result<CalendarModel>
  /**Return the raw calender without any holiday*/
    suspend  fun retrieveRawCalender(year: Int,weekend:List<DayOfWeek>): Result<CalendarModel>
}