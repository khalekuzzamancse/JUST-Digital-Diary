package faculty.data.teacher.data_sources.remote

import core.network.get.Header
import core.network.get.getRequest
import faculty.data.teacher.entity.TeacherListEntity

class TeacherListRemoteDataSource(
    private val token: String?,
    deptId: String
) {
    private val baseUrl = "https://diary.rnzgoldenventure.com/api/get/faculty-members/$deptId"

    suspend fun getTeachers(): Result<TeacherListEntity> {
        if (token == null)
           return  Result.failure(Throwable("Token is null"))
        val header = Header(key = "Authorization", value = token)
        return getRequest<TeacherListEntity>(url = baseUrl, header = header)
    }

}



