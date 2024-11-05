@file:Suppress("unused")

package core.data.entity.academic

import kotlinx.serialization.Serializable

/**
 * - Used for insert and update operations so client must need to pass the data in this format
 *
 *
 * - This is take the entry data from the client, the client itself is not allowed to decide the dept id
 * that is why not dept id is not defined here
 * - Each dept must be under a faculty, this faculty id should be pass via  api such as method,
 *  - numOfEmployee this a derived priority, never directly store in  the `Department`Table/Collection that is why not defining this property here
 * - Though client will decide under which faculty the  dept should belong,thus not taking the dept id as property
 *  because it might possible that use the same entity so insert list of dept under a single faculty
 *  @property priority client should used to sort the data
 *
 */
@Serializable
data class DepartmentWriteEntity(
    val priority: Int,
    val name: String,
    val shortname: String,
)
