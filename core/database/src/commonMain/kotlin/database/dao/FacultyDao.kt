@file:Suppress("unused")
package database.dao

import androidx.room.*
import database.schema.FacultySchema
@Dao
internal interface FacultyDao {

    @Upsert
    suspend fun upsertFaculty(faculty: FacultySchema)

    @Upsert
    suspend fun upsertFaculties(faculties: List<FacultySchema>)

    @Query("SELECT * FROM faculty_table")
    suspend fun getAllFaculties(): List<FacultySchema>

    @Query("SELECT * FROM faculty_table WHERE facultyId = :facultyId")
    suspend fun getFacultyById(facultyId: String): FacultySchema?

    @Delete
    suspend fun deleteFaculty(faculty: FacultySchema)

    @Query("DELETE FROM faculty_table")
    suspend fun clearAllFaculties()
}
