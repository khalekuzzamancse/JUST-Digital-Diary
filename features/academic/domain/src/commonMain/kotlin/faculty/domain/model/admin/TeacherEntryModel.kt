package faculty.domain.model.admin
/**
 * - Used for both add and update
 * @property priority will be used to sorting
 */
 data class TeacherEntryModel(
   val deptId:String,
   val priority:Int,
   val name: String,
   val email: String,
   val achievements: String,
   val phone: String,
   val designations: String,
   val additionalEmail: String?,
   val roomNo: String?,
   val profileImageLink: String?,
)
