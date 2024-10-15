package academic.presentationlogic.model.admin

/**
 * @param priority can be used as sorting,this is not actual database id,database will generate auto-id based on
 * department and faculty,should be used as integer
 */
internal data class TeacherEntryModel(
    val name: String,
    val email: String,
    val achievements: String,
    val phone: String,
    val designations: String,
    val deptId: String,
    val priority: String,//convert to Int,used as string to use as label in UI
    val additionalEmail: String?,
    val profileImageLink: String?,
    val roomNo: String?,
)
