@file:Suppress("unused")
package schedule.domain.model

/**
 * - Dept id should be here, instead pass via method
 */
data class ClassScheduleWriteModel(
    val session: String,
    val year:String,
    val semester:String,
    val routine:List<ClassModel>
)

