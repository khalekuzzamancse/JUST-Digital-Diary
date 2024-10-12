package faculty.domain.model.public_

/**
 * @param id for ordering or sorting
 */


data class TeacherModel(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val achievements: String,
    val designations: String,
    val profile: String? = null,
    val additionalEmail: String? = null,
    val roomNo: String?,
)
