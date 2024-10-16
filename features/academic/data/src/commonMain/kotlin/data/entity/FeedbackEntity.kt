package data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class FeedbackEntity(
    @SerialName("message") val message: String
)
