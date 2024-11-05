package auth.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResetPasswordCodeValidateEntity(
    @SerialName("email") val email: String,
    @SerialName("code") val code: String,
)