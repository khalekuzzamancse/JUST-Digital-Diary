@file:Suppress("UnUsed")

package domain.usecase

import domain.docs.CustomExceptionDoc
import domain.exception.CalendarFeatureException
import domain.model.AcademicCalendar
import domain.repository.CalenderRepository
import domain.service.CalendarService
import domain.docs.UseCaseDoc
import domain.docs.RepositoryDoc
import domain.docs.ServiceDoc


/**
 * - Use case for adding a calendar to the `Repository`
 *
 *  - `UseCase`: see [UseCaseDoc]
 *  - `Repository`: see [RepositoryDoc]
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
class AddCalendarUseCase(
    private val repository: CalenderRepository,
    private val validationService: CalendarService
) {
    /**
     * Executes the use case for adding a calendar
     *
     * @param calendar The [AcademicCalendar] to be added
     * @return [CalendarFeatureException]? Returns an exception if validation or adding fails, otherwise `null` on success
     */
    fun execute(calendar: AcademicCalendar): CalendarFeatureException? {
        val validationError = validationService.validateCalender(calendar)
        if (validationError != null)
            return validationError
        return repository.addCalender(calendar)
    }
}
