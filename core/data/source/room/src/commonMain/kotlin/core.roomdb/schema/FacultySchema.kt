@file:Suppress("unused")
package core.roomdb.schema

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @property priority denote the sorting order,it is not the primary key
 */
@Entity(tableName = "faculty_table")
internal data class FacultySchema(
    @PrimaryKey val facultyId: String,
    val priority:Int,
    val name: String,
)
