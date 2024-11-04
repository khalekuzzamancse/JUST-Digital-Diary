@file:Suppress("unused")
package core.data.entity.academic

import kotlinx.serialization.Serializable


/**
 * - Used for **insert and update operations**, where the client must provide faculty data in this format
 *
 *
 * - This entity is designed for writing data, meaning it defines the structure for how new faculty entries are created or existing ones are updated
 *
 *   - The **faculty ID** is not taken from the client because it is automatically assigned by the system or server, so it is irrelevant for the consumer to decide
 *   - The **department count** is a **derived property**, meaning it is calculated based on existing department data This field is not stored directly in the `Faculty` table/collection and is not allowed as input during faculty creation or updates
 *
 * @property priority This is used by the client to **sort** faculties or determine their importance/order
 * @property name This represents the **faculty name**, which is a required property for creating or updating a faculty entry
*/
@Serializable
 data class FacultyWriteEntity(
   val priority: Int,
   val name: String,
)