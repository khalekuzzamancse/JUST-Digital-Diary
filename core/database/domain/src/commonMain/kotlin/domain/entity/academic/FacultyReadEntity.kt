@file:Suppress("unused")
package domain.entity.academic

import kotlinx.serialization.Serializable


/**
 * - Used for **read operations** where the client will receive the faculty data in this format
 *
 *
 * - This entity provides a structured format for reading faculty data, allowing the client to display, sort, or perform other read-related actions
 * - [faculty_id] client can use for further operations such delete or update or other operations that accept a [facultyId]
 *
 * @property priority This is used by the client to **sort** the data.
 * @property faculty_id The **faculty ID** is a unique identifier used for performing operations such as fetching more details or associating departments with the faculty
 *
 * @property name This represents the **faculty name**. It is mandatory for identifying the faculty
 * @property number_of_dept This is a **derived property** representing the number of departments under the faculty
 *   This value is calculated or derived dynamically and is not stored directly in the `Faculty` table/collection
*/

@Suppress("propertyName")
@Serializable
data class FacultyReadEntity(
    val priority: Int,
    val faculty_id: String,
    val name: String,
    val number_of_dept: Int
)