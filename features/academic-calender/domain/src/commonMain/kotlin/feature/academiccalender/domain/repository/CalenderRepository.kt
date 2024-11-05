@file:Suppress("unused")

package feature.academiccalender.domain.repository


import common.docs.CustomExceptionDoc
import common.docs.RepositoryDoc
import core.customexception.CustomException
import feature.academiccalender.domain.model.AcademicCalender
import feature.academiccalender.domain.model.CalendarModel

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
    suspend fun insert(calender: AcademicCalender): Result<Unit>
    suspend fun update(calender: AcademicCalender): Result<Unit>

    /**
     * - Fetches the calendar for the current year from the database or API
     * - If holiday is not found in server or database then returns the raw calendar so that admin can add to holiday to it
     * and user can see the calendar with weekend holidays
     *
     *
     *  - On failure, return [CustomException] instead of throwing generic exceptions to  ensures concrete
     *  classes only throw exceptions defined in the 'domain' module
     * @return [Result] wrapping either the [CalendarModel] on success, or a [CustomException] on failure
     */
    suspend fun readAcademicCalender(): Result<CalendarModel>

    /**
     * - Return the raw calender of current year,without any special holiday
     * - But contain the Weekend holiday
     * */
    suspend fun readRawCalender(): Result<CalendarModel>
}