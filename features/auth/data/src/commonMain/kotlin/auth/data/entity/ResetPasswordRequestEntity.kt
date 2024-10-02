package auth.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordRequestEntity(
    @SerialName("email") val email: String,
)
