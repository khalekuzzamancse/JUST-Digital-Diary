@file:Suppress("unused")
package core.roomdb.dao

import androidx.room.*
import core.roomdb.schema.DepartmentSchema


@Dao
internal interface DepartmentDao {

    // Insert or update a department (Upsert)
    @Upsert
    suspend fun upsertDepartment(department: DepartmentSchema)

    @Upsert
    suspend fun upsertDepartments(departments: List<DepartmentSchema>)

    @Query("SELECT * FROM department_table")
    suspend fun getAllDepartments(): List<DepartmentSchema>

    @Query("SELECT * FROM department_table WHERE facultyId = :facultyId")
    suspend fun getDepartmentsByFaculty(facultyId: String): List<DepartmentSchema>

    @Query("SELECT * FROM department_table WHERE facultyId = :facultyId AND deptId = :deptId")
    suspend fun getDepartmentById(facultyId: String, deptId: String): DepartmentSchema?

    @Delete
    suspend fun deleteDepartment(department: DepartmentSchema)

    @Query("DELETE FROM department_table")
    suspend fun clearAllDepartments()
}
