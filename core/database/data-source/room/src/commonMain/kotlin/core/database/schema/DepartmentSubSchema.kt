@file:Suppress("unused")
package core.database.schema

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "department_table",
    primaryKeys = ["facultyId", "deptId"], // Composite primary key
    foreignKeys = [ForeignKey(
        entity = FacultySchema::class,
        parentColumns = ["facultyId"],
        childColumns = ["facultyId"],
        onDelete = ForeignKey.CASCADE // Optional: Set to cascade delete departments if the faculty is deleted
    )]
)
/**
 * @property id denote the sorting order,it is not the primary key
 */
data class DepartmentSchema(
    val id:Int,
    val facultyId: String, // Foreign key that references the faculty
    val deptId: String,
    val shortname: String,
    val name: String,
    val membersCount: Int
)
