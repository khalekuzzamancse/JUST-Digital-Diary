package data.entity.admin

import kotlinx.serialization.Serializable

/**
 * - Designed as Json format of  the `database:api` module
 * @property priority will be used to sorting
 */
@Serializable
internal data class TeacherResponseEntity(
    val id: String,
    val priority: Int,
    val name: String,
    val email: String,
    val additionalEmail: String?,
    val achievements: String,
    val phone: String,
    val designations: String,
    val roomNo: String?,
)
