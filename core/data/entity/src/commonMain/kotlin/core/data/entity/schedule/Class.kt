package core.data.entity.schedule

import kotlinx.serialization.Serializable

@Serializable
data class Class(
    val day: String,
    val items: List<ClassDetail>
)
