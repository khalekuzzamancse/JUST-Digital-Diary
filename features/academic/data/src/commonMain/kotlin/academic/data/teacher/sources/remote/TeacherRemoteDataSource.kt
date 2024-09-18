package academic.data.teacher.sources.remote

import academic.data.teacher.sources.remote.entity.TeacherListEntity

interface TeacherRemoteDataSource {
    suspend fun getTeachers(token:String): Result<TeacherListEntity>
}