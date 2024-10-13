package faculty.domain.model.admin

/**
 * - Used for both add and update
 * @property priority will be used to sorting
 */
 data class FacultyEntryModel(
    val priority: Int,
    val name:String,
)