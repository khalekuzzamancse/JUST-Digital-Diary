package domain.usecase

import common.docs.domain_layer.CustomExceptionDoc
import common.docs.domain_layer.RepositoryDoc
import common.docs.domain_layer.ServiceDoc
import common.docs.domain_layer.UseCaseDoc
import domain.model.CalendarModel
import domain.model.DayOfWeek
import domain.repository.CalenderRepository

/**
 * - Use case for retrieving a raw calendar from the `Repository`
 *  * Further discussion on:
 *  - `UseCase`: see [UseCaseDoc]
 *  - `Repository`: see [RepositoryDoc]
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
class GetRawCalenderUseCase(
    private val repository: CalenderRepository
) {
    suspend fun execute(year: Int, weekend: List<DayOfWeek>): Result<CalendarModel> {
        return repository.retrieveRawCalender(year, weekend)
    }
}