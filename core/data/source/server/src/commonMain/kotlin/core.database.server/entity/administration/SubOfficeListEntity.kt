package core.database.server.entity.administration

import kotlinx.serialization.Serializable

@Serializable
internal data class SubOfficeListEntity(
    val sub_offices: List<SubOfficeEntity>
)


@Serializable
internal data class SubOfficeEntity(
    val id: Int,
    val sub_office_id: String,
    val name: String,
    val office_members_count: Int
)