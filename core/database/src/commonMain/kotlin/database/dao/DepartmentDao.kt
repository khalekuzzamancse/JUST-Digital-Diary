package database.dao

import androidx.room.*
import database.schema.DepartmentSchema

@Dao
interface DepartmentDao {

    // Insert or update a department (Upsert)
    @Upsert
    suspend fun upsertDepartment(department: DepartmentSchema)

    // Insert or update multiple departments
    @Upsert
    suspend fun upsertDepartments(departments: List<DepartmentSchema>)

    // Retrieve all departments
    @Query("SELECT * FROM department_table")
    suspend fun getAllDepartments(): List<DepartmentSchema>

    // Retrieve departments by faculty name
    @Query("SELECT * FROM department_table WHERE faculty_name = :facultyName")
    suspend fun getDepartmentsByFaculty(facultyName: String): List<DepartmentSchema>

    // Retrieve a specific department by its id
    @Query("SELECT * FROM department_table WHERE id = :departmentId")
    suspend fun getDepartmentById(departmentId: Int): DepartmentSchema?

    // Delete a specific department
    @Delete
    suspend fun deleteDepartment(department: DepartmentSchema)

    // Clear all departments from the table
    @Query("DELETE FROM department_table")
    suspend fun clearAllDepartments()
}
