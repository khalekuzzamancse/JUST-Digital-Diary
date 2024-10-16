package schedule.data.entity.schedule

import kotlinx.serialization.Serializable
//need not to store duration is derived property
@Serializable
internal data class ClassDetailEntity(
    val subject: String,
    val time: String,
    val teacher: String,
)
