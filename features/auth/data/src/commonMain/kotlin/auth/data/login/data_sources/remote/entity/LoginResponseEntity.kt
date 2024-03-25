package auth.data.login.data_sources.remote.entity

import auth.data.PackageLevelAccess
import kotlinx.serialization.Serializable

/**
 * This structure should not be changed,this JSON structure of backend
 * * here is the back-end response
 * * * Make sure that it structure is same as the back-end JSON
 * {
 *     "jwt": "...",
 *     "role_id": 1,
 *     "role": "student"
 * }
 */
@PackageLevelAccess
@Serializable
internal data class LoginResponseEntity(
    val jwt: String,
    val role_id: String,
    val  role:String
)