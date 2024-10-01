package faculty.domain.repository

import common.docs.domain_layer.CustomExceptionDoc
import common.docs.domain_layer.RepositoryDoc
import faculty.domain.model.DepartmentModel
import faculty.domain.model.FacultyModel
import faculty.domain.model.TeacherModel

/**
 * Further discussion on:
 *  - `Repository`: see [RepositoryDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
interface Repository {
    /**
     * - First try to fetch data from remote database or api
     * - If for some reason failed to fetch from api or remote database then try to fetch from local database(cached)
     * @param token is null if there is no internet connection or failed to login
     * when token is null then data should be read from local repository
     * @return onFailure return custom exception
     */
    suspend fun getFaculties(): Result<List<FacultyModel>>

    /**
     * - First try to fetch data from remote database or api
     * - If for some reason failed to fetch from api or remote database then try to fetch from local database(cached)
     * @return onFailure return custom exception
     */
    suspend fun getTeachers(deptId: String): Result<List<TeacherModel>>
    suspend fun getDepartment(facultyId: String):
            Result<List<DepartmentModel>>

}
