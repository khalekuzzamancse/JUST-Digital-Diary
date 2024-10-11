package faculty.domain.repository

import common.docs.domain_layer.CustomExceptionDoc
import common.docs.domain_layer.RepositoryDoc
import faculty.domain.model.admin.DepartmentEntryModel
import faculty.domain.model.admin.FacultyEntryModel
import faculty.domain.model.admin.TeacherEntryModel
import faculty.domain.model.public_.DepartmentModel

/**
 * Further discussion on:
 *  - `Repository`: see [RepositoryDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
interface AdminRepository {
    suspend fun addFaculty(model:FacultyEntryModel):Result<Unit>
    suspend fun updateFaculty(model:FacultyEntryModel):Result<Unit>
    suspend fun addDepartment(model:DepartmentEntryModel):Result<Unit>
    suspend fun updateDepartment(model:DepartmentEntryModel):Result<Unit>
    suspend fun addTeacher(model:TeacherEntryModel):Result<Unit>
    suspend fun updateTeacher(model:TeacherEntryModel):Result<Unit>
}
