package academic.presentationlogic.model.admin

/**
 * - Used for both add and update
 * @property priority will be used to sorting,string for this layer,convert it,..
 */
internal data class FacultyEntryModel(
    val priority: String,
    val name:String,
)