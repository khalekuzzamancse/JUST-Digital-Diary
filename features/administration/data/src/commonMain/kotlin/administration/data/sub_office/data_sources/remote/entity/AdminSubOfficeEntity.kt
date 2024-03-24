package administration.data.sub_office.data_sources.remote.entity

import kotlinx.serialization.Serializable

//response JSON Format
@Serializable
data class AdminSubOfficeEntity(
    val id: Int,
    val sub_office_id: String,
    val name: String,
    val office_members_count: Int
)