package administration.data.sub_office.data_sources.remote.entity

import kotlinx.serialization.Serializable
//response JSON Format
@Serializable
data class SubOfficeListEntity(
    val sub_offices: List<AdminSubOfficeEntity>
)