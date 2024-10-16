@file:Suppress("unused")
package schedule.domain.model
data class ClassScheduleReadModel(
    val id: String,
    val deptName: String,
    val deptShortname: String,
    val session: String,
    val year:String,
    val semester:String,
    val routine:List<ClassModel>

)

