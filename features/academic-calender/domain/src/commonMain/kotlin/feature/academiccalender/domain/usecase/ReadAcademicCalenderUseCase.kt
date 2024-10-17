@file:Suppress("UnUsed")

package feature.academiccalender.domain.usecase

import common.docs.CustomExceptionDoc
import common.docs.RepositoryDoc
import common.docs.ServiceDoc
import common.docs.UseCaseDoc
import feature.academiccalender.domain.model.CalendarModel
import feature.academiccalender.domain.repository.CalenderRepository

/**
 * - Use case for retrieving a calendar to the `Repository`
 *  * Further discussion on:
 *  - `UseCase`: see [UseCaseDoc]
 *  - `Repository`: see [RepositoryDoc]
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
class ReadAcademicCalenderUseCase(
    private val repository: CalenderRepository,
) {
    /**
     * Executes the use case for retrieving a calendar for a specific year
     *
     * @param year The year for which the calendar should be retrieved
     * @return [Result] wrapping either the [CalendarModel] on success, or a [CustomException] on failure
     */
    suspend fun execute(): Result<CalendarModel> {
        return repository.readAcademicCalender()
    }
}