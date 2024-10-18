@file:Suppress("unused")
package faculty.data.entity2

import kotlinx.serialization.Serializable

/**
 * - Client will receive the data in this format
 *
 * - Though client will decide under which department the  teacher should belong,thus not taking the dept id as property
 *    because it might possible that use the same entity so insert list of dept under a single department,so api or method
 *    will take the dept id while writing operations as separate parameters
 * - Dept id will not decide by the
 * operations that accept teacher id
 */
@Suppress("propertyName")
@Serializable
data class TeacherWriteEntity(
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
