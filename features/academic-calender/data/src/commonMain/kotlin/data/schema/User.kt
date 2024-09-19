@file:Suppress("UnUsed")
package data.schema

import kotlinx.serialization.Serializable

@Serializable
internal data class User(
    val userName:String
)