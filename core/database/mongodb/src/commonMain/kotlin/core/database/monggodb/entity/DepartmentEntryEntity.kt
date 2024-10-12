package core.database.monggodb.entity

import kotlinx.serialization.Serializable


@Serializable
internal data class DepartmentEntryEntity(
    val priority: Int,
    val name: String,
    val shortName: String,
    val facultyId: String
)