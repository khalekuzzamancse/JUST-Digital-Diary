package academic.data.teacher.sources.local

import faculty.domain.teacher.model.TeacherModel

interface TeacherLocalDataSource {
    suspend fun getTeachers(deptId:String): Result< List<TeacherModel>>
    suspend fun addTeachers(deptId: String, entities: List<TeacherModel>)
}