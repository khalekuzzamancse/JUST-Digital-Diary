package administration.data.offices.data_sources.remote.entity

import kotlinx.serialization.Serializable
//same as the api JSON
@Serializable
class AdminOfficeListEntity(
    val offices: List<AdminOfficeInfoEntity>
)