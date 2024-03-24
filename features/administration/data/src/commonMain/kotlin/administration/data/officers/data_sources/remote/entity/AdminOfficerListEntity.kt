package administration.data.officers.data_sources.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class AdminOfficerListEntity(
    val officeMembers: List<AdminOfficerEntity>
)
