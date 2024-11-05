package core.database.server.entity.administration


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ServerResponseMessageEntity(
    @SerialName("message") val message: String
)
