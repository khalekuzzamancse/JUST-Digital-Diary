package academic.presentationlogic.model

/**
 * - Used for both add and update
 * @property priority will be used to sorting,string for this layer,convert it,..
 */
internal data class FacultyWriteModel(
    val priority: String,
    val name:String,
)