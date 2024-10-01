package profile.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class ProfileEntity(
    val name: String,
    val username: String,
    val email: String
)
