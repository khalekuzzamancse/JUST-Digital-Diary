@file:Suppress("unused")
package database.schema

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @property id denote the sorting order,it is not the primary key
 */
@Entity(tableName = "faculty_table")
internal data class FacultySchema(
    @PrimaryKey val facultyId: String,
    val id:Int,
    val name: String,
    val departmentCount: Int
)
