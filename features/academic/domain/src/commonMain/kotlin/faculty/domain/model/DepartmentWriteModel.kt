package faculty.domain.model

/**
 * - Used for both add and update
 *   @property priority will be used to sorting
 */
 data class DepartmentWriteModel(
    val priority: Int,
    val name: String,
    val shortname: String,
    val facultyId: String
)