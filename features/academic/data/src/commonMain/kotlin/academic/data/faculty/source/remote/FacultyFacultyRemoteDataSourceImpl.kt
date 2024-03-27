package academic.data.faculty.source.remote

import core.network.get.Header
import core.network.get.getRequest
import academic.data.faculty.source.remote.entity.FacultyListResponseEntity

internal class FacultyFacultyRemoteDataSourceImpl: FacultyRemoteDataSource {
    private val url = "https://diary.rnzgoldenventure.com/api/get/faculties"
     override suspend fun getFaculties(token:String): Result<FacultyListResponseEntity> {
         val header = Header(key = "Authorization", value = token)
        return getRequest<FacultyListResponseEntity>(url = url, header = header)
    }
}


