package faculty.domain.model.admin

/**
 * - Used for both add and update
 * @property order will be used to sorting
 */
 data class FacultyEntryModel(
    val id: String,
    val name:String,
    val order:Int
)