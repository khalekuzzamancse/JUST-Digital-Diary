package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.teachers.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.teachers.dto.TeacherInfoDTO
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.teachers.dto.TeacherListDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.post.NetworkResponse

class TeacherListRemoteDataSource(
    private val token: String?,
    deptId: String
) {
    private val baseUrl = "https://diary.rnzgoldenventure.com/api/department/$deptId"


    suspend fun getTeachers(): NetworkResponse<TeacherListDTO> {
        if (token == null)
            return NetworkResponse(
                result = null,
                isSuccess = false,
                reason = "Token is null"
            )
        val header = Header(key = "Authorization", value = token)
        return getRequest<TeacherListDTO>(url = baseUrl, header = header)
    }

//    suspend fun getTeachers(): NetworkResponse<TeacherListDTO> =
//        NetworkResponse(
//            result = TeacherListDTO(
//                message =null,
//                data = createDummyTeacherInfoList()
//            ),
//            isSuccess = true,
//            reason = null
//        )
}

private fun createDummyTeacherInfoList(): List<TeacherInfoDTO> {
    return listOf(
        TeacherInfoDTO(
            "uid001",
            "John Doe",
            "john.doe@example.com",
            "john.doe.additional@example.com",
            "profile_image_link_001",
            "Outstanding Achievements",
            "123-456-7890",
            "Assistant Professor",
            "Computer Science",
            "CS",
            "Room 101"
        ),
        TeacherInfoDTO(
            "uid002",
            "Jane Smith",
            "jane.smith@example.com",
            null,
            null,
            "Excellent Researcher",
            "987-654-3210",
            "Associate Professor",
            "Mathematics",
            "Math",
            "Room 202"
        )
        // Add more instances as needed
    )
}

