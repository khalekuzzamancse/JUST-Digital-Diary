package academic.presentationlogic.model.admin

/**
 * - Used for both add and update
 * @property order will be used to sorting
 */
internal data class FacultyEntryModel(
    val id: String,
    val name:String,
    val order:String
)