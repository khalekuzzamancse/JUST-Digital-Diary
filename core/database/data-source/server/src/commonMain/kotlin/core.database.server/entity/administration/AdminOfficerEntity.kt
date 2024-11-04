package core.database.server.entity.administration

import kotlinx.serialization.Serializable

@Serializable
internal data class AdminOfficerEntity(
    val uid: String,
    val name: String,
    val email: String,
    val role: Int,
    val phone: String,
    val achievement: String,
    val profile: String?,
    val additional_email: String?,
    val type: Int,
    val offices: List<Office>
)

@Serializable
internal data class Office(
    val name: String,
    val designation: String,
    val room_no: String,
    val present: Int
)

@Serializable
internal data class AdminOfficerListEntity(
    val officeMembers: List<AdminOfficerEntity>
)
