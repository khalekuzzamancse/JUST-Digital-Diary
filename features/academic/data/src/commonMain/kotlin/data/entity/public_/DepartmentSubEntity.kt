package data.entity.public_

import kotlinx.serialization.Serializable

@Serializable
internal data class DepartmentSubEntity(
    val name: String,
    val shortname: String,
    val designation: String,
    val room_no: String,
    val present: Int
)
