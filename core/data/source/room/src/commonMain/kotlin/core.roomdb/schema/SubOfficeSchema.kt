package core.roomdb.schema
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
@Entity(
    tableName = "sub_office_table",
    primaryKeys = ["officeId", "sub_office_id"], // Composite primary key
    foreignKeys = [ForeignKey(
        entity = OfficeSchema::class,
        parentColumns = ["officeId"],
        childColumns = ["officeId"],
        onDelete = ForeignKey.CASCADE // Optional: Set to cascade delete departments if the faculty is deleted
    )]
)
@Serializable
 internal data class SubOfficeSchema(
    val priority: Int,
     val officeId: String, // Foreign key that references the office
     val sub_office_id: String,
    val name: String,
)