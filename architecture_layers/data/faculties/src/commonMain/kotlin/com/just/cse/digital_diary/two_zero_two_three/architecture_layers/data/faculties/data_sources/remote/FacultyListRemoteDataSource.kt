package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.data_sources.remote.dto.FacultyInfoResponseDTO
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.data_sources.remote.dto.FacultyListResponseDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.post.NetworkResponse
import kotlinx.coroutines.delay

class FacultyListRemoteDataSource(
    private val token: String?
) {
    private val url = "https://diary.rnzgoldenventure.com/api/faculties"
    suspend fun getFaculties(): NetworkResponse<FacultyListResponseDTO> {
        if (token == null)
            return NetworkResponse(
                result = FacultyListResponseDTO(data = emptyList()),
                isSuccess = false,
                reason = "Token is null"
            )
        val header = Header(key = "Authorization", value = token)
        return getRequest<FacultyListResponseDTO>(url = url, header = header)

    }

//    suspend fun getFaculties(): NetworkResponse<FacultyListResponseDTO>{
//        return  NetworkResponse(
//            result = FacultyListResponseDTO(data = createDummyFacultyInfoList()),
//            isSuccess = true,
//            reason = null
//        )
//    }


}

private fun createDummyFacultyInfoList(): List<FacultyInfoResponseDTO> {
    return listOf(
        FacultyInfoResponseDTO(1, "FAC001", "John Doe", 3),
        FacultyInfoResponseDTO(2, "FAC002", "Jane Smith", 2),
        FacultyInfoResponseDTO(3, "FAC003", "Bob Johnson", 4)

    )
}