package administration.data.offices.data_sources.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class AdminOfficeInfoEntity(
    val id: Int,
    val office_id: String,
    val name: String,
    val sub_offices_count: Int
)