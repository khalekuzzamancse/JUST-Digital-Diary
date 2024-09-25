package database.schema

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(tableName = "faculty_members")
@Serializable
internal data class FacultyMemberSchema(
    @PrimaryKey val uid: String,
    val name: String,
    val email: String?,
    val role: String,
    val phone: String?,
    val achievement: String?,
    val profile: String?,
    val additionalEmail: String?,
    val type: Int,
    val departments: List<Department> // No need for @TypeConverters here if using Kotlinx Serialization
)
@Serializable
internal data class Department(
    val name: String,
    val shortname: String,
    val designation: String,
    val roomNo: String?,
    val present: Int
)

internal class DepartmentConverter {

    @TypeConverter
    fun fromDepartmentsList(departments: List<Department>?): String? {
        return if (departments == null) null else Json.encodeToString(departments)
    }

    @TypeConverter
    fun toDepartmentsList(departmentsString: String?): List<Department>? {
        return if (departmentsString == null) null else Json.decodeFromString(departmentsString)
    }
}