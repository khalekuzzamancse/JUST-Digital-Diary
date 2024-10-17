@file:Suppress("unused")
package feature.academiccalender.domain.usecase

import common.docs.CustomExceptionDoc
import common.docs.RepositoryDoc
import common.docs.ServiceDoc
import common.docs.UseCaseDoc
import feature.academiccalender.domain.model.CalendarModel
import feature.academiccalender.domain.repository.CalenderRepository

/**
 * - Raw calendar means the current a calender of given year, that is not academic
 * but using it academics calendar can be added
 * - Use case for retrieving a raw calendar from the `Repository`
 *  * Further discussion on:
 *  - `UseCase`: see [UseCaseDoc]
 *  - `Repository`: see [RepositoryDoc]
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
class ReadRawCalenderUseCase(
    private val repository: CalenderRepository
) {
    suspend fun execute(): Result<CalendarModel> {
        return repository.readRawCalender()
    }
}