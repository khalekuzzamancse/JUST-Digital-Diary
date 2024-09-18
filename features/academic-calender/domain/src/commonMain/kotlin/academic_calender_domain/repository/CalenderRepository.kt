@file:Suppress("UnUsed")
package academic_calender_domain.repository

import academic_calender_domain.exception.CalendarFeatureException
import academic_calender_domain.model.AcademicCalendar

/**
 * - This interface should be implemented by the `:data` module, which corresponds to the `infrastructure` layer in Clean Architecture
 * - It should only be used by the `Use Cases` defined within the `:domain` module
 * - The `:ui` module should not directly interact with this interface. Instead, the `:ui` module should interact with the `Use Cases`
 * - For each operation defined in this interface, there should be a corresponding `Use Case` that delegates the operation
 *   to the appropriate method in this interface
 * - The concrete implementations of this interface are allowed to directly communicate with backend services or databases
 *   However, they are not responsible for validating or verifying the data
 *   Validation and verification should be performed via `Services` before the operation is delegated to this implementation
 * - This Interface `instance` should be provided via constructor injection in the `UseCase`, allowing for
 * easy switching between automatic and manual `dependency injection`, and vice versa
 */


interface CalenderRepository {
    /**
     * Adds a calendar to the repository
     *
     * - If we were to throw an exception, it could be any type of [Exception], which might be difficult to handle uniformly
     *   That is why, instead of throwing an exception, this function returns an instance of [CalendarFeatureException]
     *   on failure, providing more control over how errors are handled.
     * - On success, it returns `null`, indicating that the operation was successful without any issues
     *
     * @param calender The [AcademicCalendar] to be added.
     * @return [CalendarFeatureException]? Returns an exception if the operation fails, otherwise returns `null` on success.
     */
    fun addCalender(calender:AcademicCalendar): CalendarFeatureException?

    /**
     * Fetches the calendar for the specified year from the database or API
     *
     * - If we were to throw an exception, it could be any type of [Exception], which would make error handling inconsistent
     *   and difficult to manage across different layers of the application
     *   That is why, instead of throwing an exception, this function returns a [Result] wrapping either the successful
     *   [AcademicCalendar] or a [CalendarFeatureException] on failure, giving more control over error handling
     * - On success, it returns a [Result.success] containing the [AcademicCalendar]
     *
     * @param year The year for which the calendar is being retrieved
     * @return [Result] wrapping either the [AcademicCalendar] on success, or a [CalendarFeatureException] on failure
     */
    fun retrieveCalender(year: Int): Result<AcademicCalendar>
}