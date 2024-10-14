package faculty.domain.repository

import common.docs.domain_layer.CustomExceptionDoc
import common.docs.domain_layer.RepositoryDoc
import faculty.domain.model.admin.DepartmentEntryModel
import faculty.domain.model.admin.FacultyEntryModel
import faculty.domain.model.admin.TeacherEntryModel
import faculty.domain.model.public_.DepartmentModel
import faculty.domain.model.public_.FacultyModel

/**
 * Further discussion on:
 *  - `Repository`: see [RepositoryDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
interface AdminRepository {
    suspend fun insertFaculty(model: FacultyEntryModel): Result<Unit>

    /**Useful for updating a faculty, need only the entry info,do need not faculty id because consumer already knew it*/
    suspend fun readFaculty(id: String): Result<FacultyEntryModel>

    /**Useful for updating a department, need only the entry info,do need not faculty id because consumer/client already knew it*/
    suspend fun readDept(id: String): Result<DepartmentEntryModel>
    suspend fun insertDept(model: DepartmentEntryModel): Result<Unit>
    suspend fun readTeacher(id: String): Result<TeacherEntryModel>
    suspend fun insertTeacher(model: TeacherEntryModel): Result<Unit>
    suspend fun getAllDept(): Result<List<DepartmentModel>>

    //TODO: UPDATE OPERATIONS
    suspend fun updateFaculty(facultyId:String,model: FacultyEntryModel): Result<Unit>
    suspend fun updateDepartment(deptId:String,model: DepartmentEntryModel): Result<Unit>
    suspend fun updateTeacher(teacherId:String,model: TeacherEntryModel): Result<Unit>
}
