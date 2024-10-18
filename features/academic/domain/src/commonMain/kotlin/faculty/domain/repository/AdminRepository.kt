package faculty.domain.repository

import common.docs.CustomExceptionDoc
import common.docs.RepositoryDoc
import faculty.domain.model.TeacherReadModel
import faculty.domain.model.DepartmentWriteModel
import faculty.domain.model.FacultyWriteModel
import faculty.domain.model.TeacherWriteModel
import faculty.domain.model.DepartmentReadModel
import faculty.domain.model.FacultyReadModel

/**
 * Further discussion on:
 *  - `Repository`: see [RepositoryDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
interface AdminRepository {
    suspend fun insertFaculty(model: FacultyWriteModel): Result<Unit>

    /**Useful for updating a faculty, need only the entry info,do need not faculty id because consumer already knew it*/
    suspend fun readFaculty(id: String): Result<FacultyReadModel>

    /**Useful for updating a department, need only the entry info,do need not faculty id because consumer/client already knew it*/
    suspend fun readDept(id: String): Result<DepartmentReadModel>
    suspend fun insertDept(model: DepartmentWriteModel): Result<Unit>
    suspend fun readTeacher(id: String): Result<TeacherReadModel>
    suspend fun insertTeacher(model: TeacherWriteModel): Result<Unit>
    suspend fun getAllDept(): Result<List<DepartmentReadModel>>

    //TODO: UPDATE OPERATIONS
    suspend fun updateFaculty(facultyId: String, model: FacultyWriteModel): Result<Unit>
    suspend fun updateDepartment(deptId: String, model: DepartmentWriteModel): Result<Unit>
    suspend fun updateTeacher(teacherId: String, model: TeacherWriteModel): Result<Unit>

    //TODO:Delete OPERATIONS
    suspend fun deleteFaculty(facultyId: String): Result<Unit>
    suspend fun deleteDepartment(deptId: String): Result<Unit>
    suspend fun deleteTeacher(teacherId: String): Result<Unit>
}
