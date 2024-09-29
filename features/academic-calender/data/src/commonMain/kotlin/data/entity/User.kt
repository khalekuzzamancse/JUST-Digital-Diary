@file:Suppress("UnUsed")
package data.entity

import kotlinx.serialization.Serializable

@Serializable
internal data class User(
    val userName:String
)