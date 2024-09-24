package auth.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ServerErrorResponseEntity(
    @SerialName("message") val message: String
)
