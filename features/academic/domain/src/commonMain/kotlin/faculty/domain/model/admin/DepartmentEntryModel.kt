package faculty.domain.model.admin

/**
 * - Used for both add and update
 *   @property priority will be used to sorting
 */
 data class DepartmentEntryModel(
    val priority: String,
    val name: String,
    val shortname: String,
    val facultyId: String
)