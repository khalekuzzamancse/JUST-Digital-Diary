package data.entity.admin

import kotlinx.serialization.Serializable

/**
 * - Used for both add and update
 *   @property priority will be used to sorting,it is not the database primary key or id
 */
@Serializable
internal data class DepartmentEntryEntity(
    val priority: String,
    val name: String,
    val shortName: String,
    val facultyId: String
)