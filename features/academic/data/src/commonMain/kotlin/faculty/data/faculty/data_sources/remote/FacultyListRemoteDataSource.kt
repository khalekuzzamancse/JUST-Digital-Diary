package faculty.data.faculty.data_sources.remote

import core.network.get.Header
import core.network.get.getRequest
import faculty.data.faculty.data_sources.remote.entity.FacultyInfoResponseEntity
import faculty.data.faculty.data_sources.remote.entity.FacultyListResponseEntity

internal class FacultyListRemoteDataSource(
    private val token: String?
) {
    private val url = "https://diary.rnzgoldenventure.com/api/get/faculties"
    suspend fun getFaculties(): Result<FacultyListResponseEntity> {
        if (token == null){
            //may be no in
            return Result.failure(Throwable("Token is null,Check Internet connection"))
        }

        val header = Header(key = "Authorization", value = token)
        return getRequest<FacultyListResponseEntity>(url = url, header = header)
    }
}



private fun createDummyFacultyInfoList(): List<FacultyInfoResponseEntity> {
    return listOf(
        FacultyInfoResponseEntity(1, "FAC001", "John Doe", 3),
        FacultyInfoResponseEntity(2, "FAC002", "Jane Smith", 2),
        FacultyInfoResponseEntity(3, "FAC003", "Bob Johnson", 4)

    )
}