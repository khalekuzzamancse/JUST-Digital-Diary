package data.source

import faculty.domain.model.public_.DepartmentModel
import faculty.domain.model.public_.FacultyModel
import faculty.domain.model.public_.TeacherModel

interface RemoteDataSource {
    suspend fun getFaculties(token: String): Result<List<FacultyModel>>
    suspend fun getDepartment(token: String, facultyId: String): Result<List<DepartmentModel>>
     suspend fun getTeachers(deptId: String,token: String): Result<List<TeacherModel>>
}
