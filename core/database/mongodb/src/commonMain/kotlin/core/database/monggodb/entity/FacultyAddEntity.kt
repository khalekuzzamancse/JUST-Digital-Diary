package core.database.monggodb.entity

import kotlinx.serialization.Serializable

@Serializable
data class FacultyAddEntity(
    val priority: Int,
    val name: String,
)