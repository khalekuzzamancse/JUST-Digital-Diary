package core.data.entity.administration
import kotlinx.serialization.Serializable

@Serializable
 data class SubOfficeReadEntity(
    val priority: Int,
    val sub_office_id: String,
    val name: String,
    val office_members_count: Int
)