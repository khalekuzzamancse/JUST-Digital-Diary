package auth.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountVerifyEntity(
    @SerialName("identifier") val identifier: String,
    @SerialName("code") val code: String,
)
