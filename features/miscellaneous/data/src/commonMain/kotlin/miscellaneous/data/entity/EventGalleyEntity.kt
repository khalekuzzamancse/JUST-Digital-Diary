package miscellaneous.data.entity

import kotlinx.serialization.Serializable

@Serializable
internal data class EventGalleyEntity(
    val name: String,
    val details:String,
    val imageLink:String
)