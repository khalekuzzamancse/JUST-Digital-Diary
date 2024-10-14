package core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import core.database.schema.FacultyMemberSchema

@Dao
internal interface FacultyMemberDao {

    @Upsert
    suspend fun upsertFacultyMember(facultyMemberSchema: FacultyMemberSchema)
    @Upsert
    suspend fun upsertFacultyMembers(facultyMembers: List<FacultyMemberSchema>)
    @Query("SELECT * FROM faculty_members WHERE uid = :uid LIMIT 1")
    suspend fun getFacultyMemberById(uid: String): FacultyMemberSchema?

    @Query("SELECT * FROM faculty_members")
    suspend fun getAllFacultyMembers(): List<FacultyMemberSchema>

    @Query("SELECT * FROM faculty_members WHERE deptId = :deptId")
    suspend fun getFacultyMembersByDeptId(deptId: String): List<FacultyMemberSchema>  // New query by deptId

    @Query("DELETE FROM faculty_members WHERE uid = :uid")
    suspend fun deleteFacultyMemberById(uid: String)

    @Query("DELETE FROM faculty_members")
    suspend fun deleteAllFacultyMembers()
}
