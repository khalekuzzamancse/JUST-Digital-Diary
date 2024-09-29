package faculty.domain.usecase

import common.docs.domain_layer.CustomExceptionDoc
import common.docs.domain_layer.RepositoryDoc
import common.docs.domain_layer.ServiceDoc
import common.docs.domain_layer.UseCaseDoc
import faculty.domain.model.TeacherModel
import faculty.domain.repository.Repository

/**
 * - Use case for retrieving a calendar to the `Repository`
 *  * Further discussion on:
 *  - `UseCase`: see [UseCaseDoc]
 *  - `Repository`: see [RepositoryDoc]
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
class RetrieveTeachersUseCase(
    private val repository: Repository
) {
    suspend fun execute(deptId:String,token:String?): Result<List<TeacherModel>> {
        return repository.getTeachers(deptId,token=token)
    }
}