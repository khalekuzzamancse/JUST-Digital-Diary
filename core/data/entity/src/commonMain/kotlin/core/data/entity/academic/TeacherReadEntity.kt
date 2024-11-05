@file:Suppress("unused")
package core.data.entity.academic

import kotlinx.serialization.Serializable

/**
 * - Client will receive the data in this format
 *
 * - [id] can be used to for further operations such update or delete or other operations that accept teacher id
 * - [dept_id] is helpful for other operations such as read the department information while updating or can done other
 * operations that accept teacher id
 */
@Suppress("propertyName")
@Serializable
data class TeacherReadEntity(
   val id: String,
   val dept_id: String,
   val priority: Int,
   val name: String,
   val email: String,
   val additional_email: String?,
   val achievements: String,
   val phone: String,
   val designations: String,
   val room_no: String?,
   val image_link:String?
)