package database.schema

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "faculty_table")
data class FacultySchema(
    @PrimaryKey val id: Int,
    val faculty_id: String,
    val name: String,
    val department_count: Int
)
