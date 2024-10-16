package domain.entity.schedule

import kotlinx.serialization.Serializable

@Serializable
data class ClassDetail(
    val subject: String,
    val time: String,
    val room: String,
    val durationInHours: Int // Duration of the class in hours
)
