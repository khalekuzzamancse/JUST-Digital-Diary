package faculty.domain.model.admin

/**
 * - Used for both add and update
 *  * @property order will be used to sorting
 */
 data class DepartmentEntryModel(
    val id: String,
    val name: String,
    val shortName: String,
    val order: Int
)