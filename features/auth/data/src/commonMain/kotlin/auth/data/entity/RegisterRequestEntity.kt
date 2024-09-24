package auth.data.entity

import kotlinx.serialization.Serializable


/**
 * this body will convert  to json as
 * Make sure that it structure is same as the back-end JSON
 *
 * * Back-end JSON :
 * ```
 *  {
 *    "name":"- -",
 *    "username":"- - ",
 *    "email":"- - ",
 *    "password":"- - "
 * }
 * ```
 */

@Serializable
internal data class RegisterRequestEntity(
    val name: String,
    val email: String,
    val username: String,
    val password: String
)