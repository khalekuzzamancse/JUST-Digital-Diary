package faculty.domain.usecase.public_

import common.docs.domain_layer.CustomExceptionDoc
import common.docs.domain_layer.RepositoryDoc
import common.docs.domain_layer.ServiceDoc
import common.docs.domain_layer.UseCaseDoc
import faculty.domain.model.public_.FacultyModel
import faculty.domain.repository.Repository

/**
 * - Use case for retrieving a calendar to the `Repository`
 *  * Further discussion on:
 *  - `UseCase`: see [UseCaseDoc]
 *  - `Repository`: see [RepositoryDoc]
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
class RetrieveFactualityUseCase(
    private val repository: Repository
) {
    suspend fun execute(): Result<List<FacultyModel>> {
        return repository.getFaculties()
    }
}