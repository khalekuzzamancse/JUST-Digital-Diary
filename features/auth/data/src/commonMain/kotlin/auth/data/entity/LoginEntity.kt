package auth.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
* this body will convert  to json as
 * Make sure that it structure is same as the back-end JSON
*
  * {
  * "identifier": "...",
  *"password": "..."
  * }
 */
@Serializable
data class LoginEntity(
    @SerialName("identifier") val email: String,
    val password: String
)