package core.data.entity.schedule

import kotlinx.serialization.Serializable
//need not to store duration is derived property
@Serializable
data class ClassDetail(
    val subject: String,
    val time: String,
    val teacher: String,
)
