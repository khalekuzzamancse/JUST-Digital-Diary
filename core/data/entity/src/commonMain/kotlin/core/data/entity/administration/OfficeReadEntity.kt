package core.data.entity.administration

import kotlinx.serialization.Serializable


@Serializable
 data class OfficeReadEntity(
    val priority: Int,
    val officeId: String,
    val name: String,
    val sub_offices_count: Int
)

