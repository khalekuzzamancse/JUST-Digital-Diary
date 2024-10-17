package faculty.domain.usecase.public_

import common.docs.CustomExceptionDoc
import common.docs.RepositoryDoc
import common.docs.ServiceDoc
import common.docs.UseCaseDoc
import faculty.domain.repository.Repository

/**
 * - Use case for retrieving a calendar to the `Repository`
 *  * Further discussion on:
 *  - `UseCase`: see [UseCaseDoc]
 *  - `Repository`: see [RepositoryDoc]
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
class ReadTeachersUseCase(
    private val repository: Repository
) {
    suspend fun execute(deptId:String)=repository.getTeachers(deptId)

}