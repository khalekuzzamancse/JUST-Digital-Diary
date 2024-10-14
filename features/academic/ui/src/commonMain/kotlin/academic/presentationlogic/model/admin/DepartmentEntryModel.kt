package academic.presentationlogic.model.admin

/**
 * - Used for both add and update
 *   @property priority will be used to sorting
 */
internal data class DepartmentEntryModel(
    val priority: String,
    val name: String,
    val shortname: String,
    val facultyId: String,

    )