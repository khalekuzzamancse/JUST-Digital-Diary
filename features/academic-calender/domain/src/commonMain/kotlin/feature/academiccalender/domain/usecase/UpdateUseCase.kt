@file:Suppress("unused")

package feature.academiccalender.domain.usecase

import common.docs.CustomExceptionDoc
import common.docs.RepositoryDoc
import common.docs.ServiceDoc
import common.docs.UseCaseDoc
import feature.academiccalender.domain.model.AcademicCalender
import feature.academiccalender.domain.repository.CalenderRepository
import core.customexception.CustomException


/**
 * - Use case for adding a calendar to the `Repository`
 *
 *  - `UseCase`: see [UseCaseDoc]
 *  - `Repository`: see [RepositoryDoc]
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
class UpdateUseCase(
    private val repository: CalenderRepository,
) {
    /**
     * Executes the use case for adding a calendar
     *
     * @param calendar The [AcademicCalender] to be added
     * @return [CustomException]? Returns an exception if validation or adding fails, otherwise `null` on success
     */
   suspend fun execute(calendar: AcademicCalender):Result<Unit> {
        return repository.update(calendar)
    }
}
