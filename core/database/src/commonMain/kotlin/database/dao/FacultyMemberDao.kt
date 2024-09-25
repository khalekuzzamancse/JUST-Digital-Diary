package database.dao

import androidx.room.*
import database.schema.FacultyMemberSchema

@Dao
internal interface FacultyMemberDao {

    @Upsert
    suspend fun upsertFacultyMember(facultyMemberSchema: FacultyMemberSchema)

    @Query("SELECT * FROM faculty_members WHERE uid = :uid LIMIT 1")
    suspend fun getFacultyMemberById(uid: String): FacultyMemberSchema?

    @Query("SELECT * FROM faculty_members")
    suspend fun getAllFacultyMembers(): List<FacultyMemberSchema>

    @Query("DELETE FROM faculty_members WHERE uid = :uid")
    suspend fun deleteFacultyMemberById(uid: String)

    @Query("DELETE FROM faculty_members")
    suspend fun deleteAllFacultyMembers()
}
