package academic.presentationlogic.model

/**
 * - Used for both add and update
 *   @property priority will be used to sorting
 */
internal data class DepartmentWriteModel(
    val priority: String,
    val name: String,
    val shortname: String,
    val facultyId: String,

    ) {
    companion object {
        fun empty() = DepartmentWriteModel(
            priority = "",
            name = "",
            shortname = "",
            facultyId = ""
        )
    }
}