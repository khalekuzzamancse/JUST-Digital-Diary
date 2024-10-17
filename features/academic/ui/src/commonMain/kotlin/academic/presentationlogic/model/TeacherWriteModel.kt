package academic.presentationlogic.model

/**
 * @param priority can be used as sorting,this is not actual database id,database will generate auto-id based on
 * department and faculty,should be used as integer
 */
internal data class TeacherWriteModel(
    val name: String,
    val email: String,
    val achievements: String,
    val phone: String,
    val designations: String,
    val deptId: String,
    val priority: String, // Convert to Int later
    val additionalEmail: String?,
    val profileImageLink: String?,
    val roomNo: String?,
) {
    companion object {
        fun empty() = TeacherWriteModel(
            name = "",
            email = "",
            achievements = "",
            phone = "",
            designations = "",
            deptId = "",
            priority = "0", // Defaulting priority to "0" as a string
            additionalEmail = null,
            profileImageLink = null,
            roomNo = null
        )
    }
}
