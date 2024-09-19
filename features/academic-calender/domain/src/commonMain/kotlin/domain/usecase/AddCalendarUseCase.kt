@file:Suppress("UnUsed")

package domain.usecase

import common.docs.domain_layer.CustomExceptionDoc
import common.docs.domain_layer.RepositoryDoc
import common.docs.domain_layer.ServiceDoc
import common.docs.domain_layer.UseCaseDoc
import domain.exception.CalendarFeatureException
import domain.model.AcademicCalendar
import domain.model.User
import domain.repository.CalenderRepository
import domain.service.UserService


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
    private val service: UserService,
) {
    /**
     * Executes the use case for adding a calendar
     *
     * @param calendar The [AcademicCalendar] to be added
     * @return [CalendarFeatureException]? Returns an exception if validation or adding fails, otherwise `null` on success
     */
    fun execute(calendar: AcademicCalendar,user: User): CalendarFeatureException? {
        val validationError = service.validateAuthenticity(user)
        if (validationError != null)
            return validationError
        return repository.addCalender(calendar)
    }
}
