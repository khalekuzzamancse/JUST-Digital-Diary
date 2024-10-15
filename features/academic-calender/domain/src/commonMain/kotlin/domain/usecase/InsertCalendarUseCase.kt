@file:Suppress("UnUsed")

package domain.usecase

import common.docs.domain_layer.CustomExceptionDoc
import common.docs.domain_layer.RepositoryDoc
import common.docs.domain_layer.ServiceDoc
import common.docs.domain_layer.UseCaseDoc
import domain.exception.CustomException
import domain.model.AcademicCalendar
import domain.model.AcademicCalender2
import domain.repository.CalenderRepository


/**
 * - Use case for adding a calendar to the `Repository`
 *
 *  - `UseCase`: see [UseCaseDoc]
 *  - `Repository`: see [RepositoryDoc]
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
class InsertCalendarUseCase(
    private val repository: CalenderRepository,
) {
    /**
     * Executes the use case for adding a calendar
     *
     * @param calendar The [AcademicCalendar] to be added
     * @return [CustomException]? Returns an exception if validation or adding fails, otherwise `null` on success
     */
    fun execute(calendar: AcademicCalender2):Result<Unit> {
        return repository.addCalender(calendar)
    }
}
