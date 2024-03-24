package auth.data.login.data_sources.remote.entity

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
data class LoginRequestEntity(
    val identifier: String,
    val password: String
)