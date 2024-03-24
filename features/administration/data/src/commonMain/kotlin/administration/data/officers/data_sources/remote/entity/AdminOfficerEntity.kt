package administration.data.officers.data_sources.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class AdminOfficerEntity (
    val uid: String,
    val name: String,
    val email: String,
    val additional_email: String?,
    val profile_image: String?,
    val achievement: String,
    val phone: String?,
    val designations: String,
    val room_no: String
)