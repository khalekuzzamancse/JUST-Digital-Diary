package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.data_sources.remote.dto.FacultyInfoResponseDTO
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.data_sources.remote.dto.FacultyListResponseDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.post.NetworkResponse
import kotlinx.coroutines.delay

class FacultyListRemoteDataSource {
    private val token =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxOTExMDEuY3NlOGFlNDdkYTdkY2VkIiwicm9sZV9pZCI6MTMsImlhdCI6MTcwNjQxOTc3OSwiZXhwIjoxNzA2NTkyNTc5fQ.AxaN98L4p_lcxqJR5wql-qIVJTHGdN1Ju4Q584PX1iw"
    private val url = "https://diary.rnzgoldenventure.com/api/faculties"
    private val header = Header(key = "Authorization", value = token)

    //    suspend fun getFaculties(): NetworkResponse<FacultyListResponseDTO> = getRequest<FacultyListResponseDTO>(url=url, header=header)
    suspend fun getFaculties(): NetworkResponse<FacultyListResponseDTO>{
        return  NetworkResponse(
            result = FacultyListResponseDTO(data = createDummyFacultyInfoList()),
            isSuccess = true,
            reason = null
        )
    }


}

private fun createDummyFacultyInfoList(): List<FacultyInfoResponseDTO> {
    return listOf(
        FacultyInfoResponseDTO(1, "FAC001", "John Doe", 3),
        FacultyInfoResponseDTO(2, "FAC002", "Jane Smith", 2),
        FacultyInfoResponseDTO(3, "FAC003", "Bob Johnson", 4)

    )
}