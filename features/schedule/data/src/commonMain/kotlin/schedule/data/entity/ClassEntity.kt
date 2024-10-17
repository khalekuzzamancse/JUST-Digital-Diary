package schedule.data.entity

import kotlinx.serialization.Serializable

@Serializable
internal data class ClassEntity(
    val day: String,
    val items: List<ClassDetailEntity>
)
