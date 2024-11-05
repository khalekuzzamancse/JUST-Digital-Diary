package core.roomdb.schema

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
@Entity(tableName = "admin_employee")
@Serializable
 internal data class AdminOfficerSchema(
  @PrimaryKey val id: String,
  val sub_office_id:String,//foreign key
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


