package data.source

import faculty.domain.model.DepartmentModel
import faculty.domain.model.FacultyModel
import faculty.domain.model.TeacherModel

interface RemoteDataSource {
    suspend fun getFaculties(token: String): Result<List<FacultyModel>>
    suspend fun getDepartment(token: String, facultyId: String): Result<List<DepartmentModel>>
     suspend fun getTeachers(deptId: String,token: String): Result<List<TeacherModel>>
}
