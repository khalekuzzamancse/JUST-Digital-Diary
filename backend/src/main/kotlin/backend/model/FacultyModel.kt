package backend.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FacultyModel(
    @SerialName("id") val id: Int,
    @SerialName("faculty_id") val facultyId: String,
    @SerialName("name") val name: String,
    @SerialName("department_count") val departmentCount: Int
)
