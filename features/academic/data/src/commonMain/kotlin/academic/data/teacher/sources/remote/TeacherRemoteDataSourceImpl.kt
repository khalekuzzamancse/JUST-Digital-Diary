package academic.data.teacher.sources.remote

import _old.network.get.Header
import _old.network.get.getRequest
import academic.data.teacher.sources.remote.entity.TeacherListEntity

class TeacherRemoteDataSourceImpl(
    deptId: String
) : TeacherRemoteDataSource {
    private val baseUrl = "https://diary.rnzgoldenventure.com/api/get/faculty-members/$deptId"
    override suspend fun getTeachers(token: String): Result<TeacherListEntity> {
        val header = Header(key = "Authorization", value = token)
        return getRequest<TeacherListEntity>(url = baseUrl, header = header)
    }

}



