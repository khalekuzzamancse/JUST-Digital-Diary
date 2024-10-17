package faculty.domain.model

/**
 * - Used for both add and update
 * @property priority will be used to sorting
 */
 data class FacultyWriteModel(
    val priority: Int,
    val name:String,
)