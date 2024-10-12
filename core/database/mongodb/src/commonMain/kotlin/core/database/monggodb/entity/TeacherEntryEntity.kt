package core.database.monggodb.entity

import kotlinx.serialization.Serializable

/**
 * - Used for both add and update
 * @property priority will be used to sorting
 */
@Serializable
internal data class TeacherEntryEntity(
    val deptId: String,
    val priority: String,
    val name: String,
    val email: String,
    val additionalEmail: String,
    val achievements: String,
    val phone: String,
    val designations: String,
    val roomNo: String,
)
