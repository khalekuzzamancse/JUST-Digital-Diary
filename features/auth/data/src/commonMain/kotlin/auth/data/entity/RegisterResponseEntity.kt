package auth.data.entity

import auth.data.PackageLevelAccess
import kotlinx.serialization.Serializable
/**
 * this body will convert  to json as
 * Make sure that it structure is same as the back-end JSON
 *
 * * on Success JSON -> { "message": "- - -" }
 * * on Failure JSON -> { "error": "- - -" }
 */
@PackageLevelAccess
@Serializable
internal data class RegisterResponseEntity(val message: String)