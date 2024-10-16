@file:Suppress("UnUsed")

package feature.academiccalender.domain.repository


import common.docs.domain_layer.CustomExceptionDoc
import common.docs.domain_layer.RepositoryDoc
import feature.academiccalender.domain.model.AcademicCalender2
import feature.academiccalender.domain.model.CalendarModel
import feature.academiccalender.domain.model.DayOfWeek

/**
 * Further discussion on:
 *  - `Repository`: see [RepositoryDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */

interface CalenderRepository {
    /**
     * Adds a calendar to the repository
     *
     *  - On failure, return [CustomException] instead of throwing generic exceptions to  ensures concrete
     *  classes only throw exceptions defined in the 'domain' module
     * @return onSuccess, it return null
     */
    suspend fun insert(calender: AcademicCalender2): Result<Unit>
    suspend fun update(calender: AcademicCalender2): Result<Unit>

    /**
     * - Fetches the calendar for the current year from the database or API
     * - If holiday is not found in server or database then returns the raw calendar so that admin can add to holiday to it
     * and user can see the calendar with weekend holidays
     *
     *
     *  - On failure, return [CustomException] instead of throwing generic exceptions to  ensures concrete
     *  classes only throw exceptions defined in the 'domain' module
     * @param year The year for which the calendar is being retrieved
     * @return [Result] wrapping either the [CalendarModel] on success, or a [CustomException] on failure
     */
    suspend fun readAcademicCalender(): Result<CalendarModel>

    /**
     * - Return the raw calender of current year,without any special holiday
     * - But contain the Weekend holiday
     * */
    suspend fun readRawCalender(): Result<CalendarModel>
}