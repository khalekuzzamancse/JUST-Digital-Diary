package academic.data.faculty.source.remote

import academic.data.faculty.source.remote.entity.FacultyListResponseEntity

interface FacultyRemoteDataSource {
    suspend fun getFaculties(token: String): Result<FacultyListResponseEntity>
}
