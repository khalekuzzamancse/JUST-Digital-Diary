package core.data.entity.administration

import kotlinx.serialization.Serializable

@Serializable
 data class AdminOfficerReadEntity(
   val id: String,
   val name: String,
   val email: String,
   val phone: String,
   val achievement: String,
   val profile: String?,
   val additional_email: String?,
   val priority: Int,
   val designation: String,
   val room_no: String,
)


