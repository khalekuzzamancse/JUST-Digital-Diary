package auth.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordConfirmEntity(
    @SerialName("email") val email: String,
    @SerialName("code") val code: String,
    @SerialName("password") val password: String,
)
