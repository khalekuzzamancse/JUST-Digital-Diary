package auth.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This structure should not be changed,this JSON structure of backend
 * * here is the back-end response
 * * * Make sure that it structure is same as the back-end JSON
 * {
 *     ```
 *     "jwt": "...",
 *     "role_id": 1,
 *     "role": "student"
 *     ```
 * }
 */
@Serializable
internal data class LoginResponseEntity(
    @SerialName("jwt") val token: String,
    @SerialName("role_id") val roleId: Int,
    @SerialName("role") val role: String,
    @SerialName("username") val username: String
)