@file:Suppress("UnUsed")

package academic_calender_domain.service

import academic_calender_domain.exception.CalendarFeatureException
import academic_calender_domain.model.AcademicCalendar
import academic_calender_domain.model.User

/**
 * Why service:
 * - According to Clean Architecture principles, use cases are not responsible for data validation or checking authenticity
 * - Before invoking a `UseCase`, the consumer must validate the data, which is the responsibility of the services
 * - Services contain methods for performing specific validation tasks, such as verifying data integrity or checking the authenticity of a request
 * - In the domain layer, only the interfaces for these services are defined, while the implementation is handled in the data layer
 * - This separation ensures that use cases remain focused on their core business logic, promoting a clean separation of concerns
 * - The services are defined as interfaces because certain validations, such as authenticity checks, may require database access,
 *   which will be handled in the implementation by the data layer
 *
 * Who should use it:
 * - The `Controller` in the `UI` layer should invoke the service to validate data before passing it to the use case
 * - Any `consumer` of the `UseCase` should use the services to ensure that the data is valid before executing the use case
 */

interface CalendarService {

    /**
     * Validates the content of the [AcademicCalendar] before it is added or updated in the repository\
     *
     * Why it returns an exception instead of throwing:
     * - If we were to throw an exception, it could be any type of [Exception], which might be difficult to handle uniformly
     * @return on success return null otherwise return  [CalendarFeatureException]
     *
     * @param calendar The [AcademicCalendar] to be validated
     * @return [CalendarFeatureException]? Returns a [CalendarFeatureException] on validation failure, or `null` if valid
     */

    fun validateCalender(calendar: AcademicCalendar): CalendarFeatureException?

    /**
     * - Validates user has right permission or not for adding/updating or fetching the calender to database
     *
     * Why it returns an exception instead of throwing:
     * - If we were to throw an exception, it could be any type of [Exception], which might be difficult to handle uniformly
     * @return on success return null otherwise return  [CalendarFeatureException]
     */
    fun validateAuthenticity(user: User): CalendarFeatureException?
    /**
     * - Validates user has right permission or not for adding/updating or fetching the calender to database
     *
     * Why it returns an exception instead of throwing:
     * - If we were to throw an exception, it could be any type of [Exception], which might be difficult to handle uniformly
     * @return on success return null otherwise return  [CalendarFeatureException]
     */
     fun validateYear(year: Int):  CalendarFeatureException?


}
