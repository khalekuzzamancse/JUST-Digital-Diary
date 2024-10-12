package core.database.monggodb.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MessageEntity(
    @SerialName("message") val message: String
)
