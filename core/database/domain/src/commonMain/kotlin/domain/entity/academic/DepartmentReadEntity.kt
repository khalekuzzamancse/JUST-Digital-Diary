@file:Suppress("unused")
package domain.entity.academic

import kotlinx.serialization.Serializable

/**
 *  Used for only read operations such so client will receive the data in this format
 *
 *
 *  @property priority client should used to sort the data
 *  @property number_of_employee this a derived priority, never directly store in  the `Department`Table/Collection
 *  @property dept_id will help to client to request some further operations via this, such as deleting, etc
 *  @property faculty_id will help to client to request some further operations via this, such as reading the faculty info while updating this department
 */
@Suppress("propertyName")
@Serializable
data class DepartmentReadEntity(
    val priority: Int,
    val name: String,
    val shortname: String,
    val number_of_employee: Int,
    val dept_id: String,
    val faculty_id: String
)