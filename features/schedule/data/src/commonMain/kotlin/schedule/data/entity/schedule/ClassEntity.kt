package schedule.data.entity.schedule

import kotlinx.serialization.Serializable

@Serializable
internal data class ClassEntity(
    val day: String,
    val items: List<ClassDetailEntity>
)
