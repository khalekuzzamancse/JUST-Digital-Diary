@file:Suppress("unused")
package domain.api
import core.data.entity.academic.*
import core.data.entity.FeedbackMessageEntity


/**
 * - Define the set of apis that should be implemented by the database, regardless of database type
 * such as mongodb,firebase or room should implement this, as a result it the underlying implementation of
 * database will be hidden to client or consumer
 * - More ever easily can switch the different database because they implementation the same api
 * - The presentation or the api/endpoint  layer should be implemented this, and must delegate the
 * call to a database
 */
interface AcademicRemoteApi {
    /**
     * @return on success return JSON version of list of [FacultyReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readFacultiesOrThrow(): List<FacultyReadEntity>

    /**
     * Insert a faculty.
     * @param json the JSON string representing the [FacultyWriteEntity].
     * @return on success return the JSON version of the inserted [FacultyReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun insertFacultyOrThrow(entity: FacultyWriteEntity)
    suspend fun insertFacultiesOrThrow(entities:List<FacultyWriteEntity>)


    /**
     * Read a faculty by its ID.
     * @param id the ID of the faculty to read.
     * @return on success return the JSON version of the [FacultyReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readFacultyByIdOrThrow(id: String): FacultyReadEntity

    /**@return on failure/success return the JSON version of [FeedbackMessageEntity]*/
    suspend fun  deleteFacultyOrThrow(id: String)



    /**
     * Insert a department under a faculty.
     * @param facultyId the ID of the faculty under which the department is added.
     * @param json the JSON string representing the [DepartmentWriteEntity].
     * @return on success return the JSON version of the inserted [DepartmentReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */


    suspend fun insertDeptOrThrow(facultyId: String, entity: DepartmentWriteEntity)
    suspend fun insertDeptsOrThrow(facultyId: String, entities:List<DepartmentWriteEntity>)

    /**
     * @return on success return JSON version of list of [DepartmentReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readAllDeptOrThrow(): List<DepartmentReadEntity>

    /**
     * Get departments under a specific faculty.
     * @param facultyId the ID of the faculty.
     * @return on success return the JSON version of list of [DepartmentReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */


    suspend fun readDeptsOrThrow(facultyId: String): List<DepartmentReadEntity>

    /**
     * Read a department by its ID.
     * @param id the ID of the department to read.
     * @return on success return the JSON version of the [DepartmentReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readDeptOrThrow(id: String): DepartmentReadEntity




    /**@return on failure/success return the JSON version of [FeedbackMessageEntity]*/
    suspend fun  deleteDepartmentOrThrow(id: String)




    /**
     * Insert a teacher under a department.
     * @param deptId the ID of the department under which the teacher is added.
     * @param json the JSON string representing the [TeacherWriteEntity].
     * @return on success return the JSON version of the inserted [TeacherReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun insertTeacherOrThrow(deptId: String, entity: TeacherWriteEntity)
    suspend fun insertTeachersOrThrow(deptId: String, entities:List<TeacherWriteEntity>)
    /**
     * @return on success return JSON version of list of [TeacherReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readAllTeacherOrThrow(): List<TeacherReadEntity>

    /**
     * Get teachers under a specific department.
     * @param deptId the ID of the department.
     * @return on success return the JSON version of list of [TeacherReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readTeachersOrThrow(deptId: String): List<TeacherReadEntity>

    /**
     * Read a teacher by their ID.
     * @param teacherId the ID of the teacher to read.
     * @return on success return the JSON version of the [TeacherReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readTeacherOrThrow(teacherId: String): TeacherReadEntity

    suspend fun updateFacultyOrThrow(facultyId: String, entity:FacultyWriteEntity)
    suspend fun updateDeptOrThrow(deptId: String, entity:DepartmentWriteEntity)
    suspend fun updateTeacherOrThrow(teacherId: String, entity: TeacherWriteEntity)


    /**@return on failure/success return the JSON version of [FeedbackMessageEntity]*/
    suspend fun  deleteTeacherOrThrow(id: String)

}
