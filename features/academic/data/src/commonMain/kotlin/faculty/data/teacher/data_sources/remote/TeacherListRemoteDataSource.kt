package faculty.data.teacher.data_sources.remote

import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest2
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
        return getRequest2<TeacherListEntity>(url = baseUrl, header = header)
    }

}



