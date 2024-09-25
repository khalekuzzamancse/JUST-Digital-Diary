@file:Suppress("unused")
package database.apis

import database.entity.DepartmentEntity
import database.entity.FacultyEntity
import database.entity.FacultyMemberEntity

/**
 * Academic API for managing faculties, departments, and members.
 * Exposes methods to retrieve and add data, with proper error handling.
 */
interface AcademicApi {

    /**
     * Retrieves all faculties.
     * @return Result containing either a list of FacultyEntity or an exception in case of failure.
     */
    suspend fun getAllFaculties(): Result<List<FacultyEntity>>

    /**
     * Retrieves a list of departments by faculty ID.
     * @param facultyId The ID of the faculty to retrieve departments for.
     * @return Result containing either a list of DepartmentEntity or an exception in case of failure.
     */
    suspend fun getDepartmentsByFacultyId(facultyId: String): Result<List<DepartmentEntity>>

    /**
     * Retrieves a list of faculty members by department ID.
     * @param deptId The ID of the department to retrieve members for.
     * @return Result containing either a list of FacultyMemberEntity or an exception in case of failure.
     */
    suspend fun getFacultyMembersByDeptId(deptId: String): Result<List<FacultyMemberEntity>>

    /**
     * Adds a list of faculties to the system.
     * Throws an exception if the operation fails.
     * @param faculties List of faculties to be added.
     */
    suspend fun addFaculties(faculties: List<FacultyEntity>)

    /**
     * Adds a list of departments to the system.
     * Throws an exception if the operation fails.
     * @param departments List of departments to be added.
     */
    suspend fun addDepartments(departments: List<DepartmentEntity>)

    /**
     * Adds a list of faculty members to the system.
     * Throws an exception if the operation fails.
     * @param facultyMembers List of faculty members to be added.
     */
    suspend fun addFacultyMembers(facultyMembers: List<FacultyMemberEntity>)
}
