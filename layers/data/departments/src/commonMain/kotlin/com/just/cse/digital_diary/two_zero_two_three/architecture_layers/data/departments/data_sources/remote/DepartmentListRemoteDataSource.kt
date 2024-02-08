package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.departments.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.departments.dto.DepartmentInfoDTO
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.departments.dto.DepartmentListResponseDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.post.NetworkResponse

class DepartmentListRemoteDataSource(
    private val token: String?,
    facultyId: String
) {
    private val baseUrl = "https://diary.rnzgoldenventure.com/api/dept/$facultyId"

    suspend fun getDepartments(): NetworkResponse<DepartmentListResponseDTO> {
        if (token == null)
            return NetworkResponse(
                result = null,
                isSuccess = false,
                reason = "Token is null"
            )
        val header = Header(key = "Authorization", value = token)
        return getRequest<DepartmentListResponseDTO>(url = baseUrl, header = header)
    }
//    suspend fun getDepartments(): NetworkResponse<DepartmentListResponseDTO> =
//        NetworkResponse(
//            result = DepartmentListResponseDTO(
//                message = "",
//                data = createDummyDepartmentInfoList()
//            ),
//            isSuccess = true,
//            reason = null
//
//        )
}

private fun createDummyDepartmentInfoList(): List<DepartmentInfoDTO> {
    return listOf(
        DepartmentInfoDTO(1, "DEPT001", "Computer Science", "CS", 10),
        DepartmentInfoDTO(2, "DEPT002", "Mathematics", "Math", 8),
        DepartmentInfoDTO(3, "DEPT003", "Physics", "Phys", 12)

    )
}