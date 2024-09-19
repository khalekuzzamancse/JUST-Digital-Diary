@file:Suppress("UnUsed")

package domain.usecase

import common.docs.domain_layer.CustomExceptionDoc
import domain.model.AcademicCalendar
import domain.repository.CalenderRepository
import domain.service.CalendarService

import domain.exception.CalendarFeatureException
import common.docs.domain_layer.UseCaseDoc
import common.docs.domain_layer.RepositoryDoc
import common.docs.domain_layer.ServiceDoc

/**
 * - Use case for retrieving a calendar to the `Repository`
 *  * Further discussion on:
 *  - `UseCase`: see [UseCaseDoc]
 *  - `Repository`: see [RepositoryDoc]
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
class RetrieveCalendarUseCase(
    private val repository: CalenderRepository,
    private val validationService: CalendarService
) {
    /**
     * Executes the use case for retrieving a calendar for a specific year
     *
     * @param year The year for which the calendar should be retrieved
     * @return [Result] wrapping either the [AcademicCalendar] on success, or a [CalendarFeatureException] on failure
     */
    fun execute(year: Int): Result<AcademicCalendar> {
        // Validate the year before retrieving the calendar
        val validationError = validationService.validateYear(year)
        if (validationError != null) {
            return Result.failure(validationError)
        }

        // Retrieve the calendar from the repository
        return repository.retrieveCalender(year)
    }
}