package academic.presentationlogic.model

/**
 * @property deptId useful for admin to load the dept info when updating a selected teacher from the list
 */
internal data class TeacherReadModel(
    val id:String="",
    val deptId:String="",
    val name: String,
    val email: String,
    val additionalEmail: String?,
    val achievements: String,
    val phone: String,
    val designations: String,
    val roomNo: String?,
    val profileImageLink: String? = "https://static.just.edu.bd/images/public/teachers/1603270274986_900.jpeg",
)
