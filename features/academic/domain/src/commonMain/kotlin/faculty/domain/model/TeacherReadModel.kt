@file:Suppress("unused")
package faculty.domain.model


data class TeacherReadModel(
   val id: String,
   val deptId: String,
   val name: String,
   val email: String,
   val additionalEmail: String?,
   val achievements: String,
   val phone: String,
   val designations: String,
   val roomNo: String?,
   val imageLink:String?,
   val priority:Int,
)