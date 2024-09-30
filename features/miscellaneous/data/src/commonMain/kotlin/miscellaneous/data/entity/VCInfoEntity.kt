package miscellaneous.data.entity

import kotlinx.serialization.Serializable

@Serializable
internal data class VCInfoEntity(
    val name: String,
    val details: String,
    val message: String,
    val imageUrl: String
)
