package database.schema

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "department_table")
data class DepartmentSchema(
    @PrimaryKey val id: Int,           // Primary key for each department
    val dept_id: String,               // Department unique identifier
    val shortname: String,             // Short name of the department
    val name: String,                  // Full name of the department
    val membersCount: Int,             // Number of members in the department
    val faculty_name: String           // The name of the faculty the department belongs to
)

