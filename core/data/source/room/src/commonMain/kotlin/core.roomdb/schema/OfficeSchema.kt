package core.roomdb.schema

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "office_table")
@Serializable
 internal data class OfficeSchema(
    val priority: Int,
    @PrimaryKey val officeId: String,
    val name: String,
)

