package data.entity.admin

import kotlinx.serialization.Serializable

/**
 * - Used for both add and update
 * @property priority will be used to sorting,this not the database id or primary key
 */
@Serializable
internal data class FacultyEntryEntity(
    val priority: String,
    val name:String,
)