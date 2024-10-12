package core.database.monggodb.presentation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FacultyRetrieveModel(
    @SerialName("id") val priority: Int,
    @SerialName("faculty_id") val facultyId: String,
    @SerialName("name") val name: String,
    @SerialName("department_count") val departmentCount: Int
)

