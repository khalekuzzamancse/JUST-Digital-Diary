package auth.data.register.remote.entity

import auth.data.PackageLevelAccess
import kotlinx.serialization.Serializable


/**
 * this body will convert  to json as
 * Make sure that it structure is same as the back-end JSON
 *
 * * Back-end JSON :
 * *
 * * {
 *  *   "name":"- -",
 *   *  "username":"- - ",
 *   *  "email":"- - ",
 *   *  "password":"- - "
 * * }
 */
@PackageLevelAccess
@Serializable
internal data class RegisterRequestEntity(
    val name: String,
    val email: String,
    val username: String,
    val password: String
)