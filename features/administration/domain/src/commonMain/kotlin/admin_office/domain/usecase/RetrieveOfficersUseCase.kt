package admin_office.domain.usecase

import admin_office.domain.model.AdminOfficerModel
import admin_office.domain.repository.Repository

import common.docs.domain_layer.CustomExceptionDoc
import common.docs.domain_layer.RepositoryDoc
import common.docs.domain_layer.ServiceDoc
import common.docs.domain_layer.UseCaseDoc

/**
 * - Use case for retrieving a calendar to the `Repository`
 *  * Further discussion on:
 *  - `UseCase`: see [UseCaseDoc]
 *  - `Repository`: see [RepositoryDoc]
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
class RetrieveOfficersUseCase(
    private val repository: Repository
) {
    suspend fun execute(subOfficeId: String): Result<List<AdminOfficerModel>> {
        return repository.getOfficers(subOfficeId)
    }
}