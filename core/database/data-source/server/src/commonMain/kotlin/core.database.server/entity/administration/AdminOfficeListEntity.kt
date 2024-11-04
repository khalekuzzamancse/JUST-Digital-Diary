package core.database.server.entity.administration

import kotlinx.serialization.Serializable

@Serializable
internal class AdminOfficeListEntity(
    val offices: List<OfficeEntity>
)

@Serializable
internal data class OfficeEntity(
    val id: Int,
    val office_id: String,
    val name: String,
    val sub_offices_count: Int
)

