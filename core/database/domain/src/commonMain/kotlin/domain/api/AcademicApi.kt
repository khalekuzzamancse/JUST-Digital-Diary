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
interface AcademicApi {
    /**
     * @return on success return JSON version of list of [FacultyReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readFaculties(): String

    /**
     * Insert a faculty.
     * @param json the JSON string representing the [FacultyWriteEntity].
     * @return on success return the JSON version of the inserted [FacultyReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun insertFaculty(json: String): String
    /**
     * Insert a faculty if not exits otherwise update
     * - This is useful for caching such as room database where it direcly insert the
     * @param json the JSON string representing the [FacultyReadEntity]
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun upsetFacultyList(json: String): String

    /**
     * Read a faculty by its ID.
     * @param id the ID of the faculty to read.
     * @return on success return the JSON version of the [FacultyReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readFacultyById(id: String): String

    /**@return on failure/success return the JSON version of [FeedbackMessageEntity]*/
    suspend fun  deleteFaculty(id: String):String




    /**
     * Insert a department under a faculty.
     * @param facultyId the ID of the faculty under which the department is added.
     * @param json the JSON string representing the [DepartmentWriteEntity].
     * @return on success return the JSON version of the inserted [DepartmentReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */


    suspend fun insertDept(facultyId: String, json: String): String

    /**
     * @return on success return JSON version of list of [DepartmentReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readAllDept(): String

    /**
     * Get departments under a specific faculty.
     * @param facultyId the ID of the faculty.
     * @return on success return the JSON version of list of [DepartmentReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */


    suspend fun readDeptsUnderFaculty(facultyId: String): String

    /**
     * Read a department by its ID.
     * @param id the ID of the department to read.
     * @return on success return the JSON version of the [DepartmentReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readDeptById(id: String): String




    /**@return on failure/success return the JSON version of [FeedbackMessageEntity]*/
    suspend fun  deleteDepartment(id: String):String




    /**
     * Insert a teacher under a department.
     * @param deptId the ID of the department under which the teacher is added.
     * @param json the JSON string representing the [TeacherWriteEntity].
     * @return on success return the JSON version of the inserted [TeacherReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun insertTeacher(deptId: String, json: String): String

    /**
     * @return on success return JSON version of list of [TeacherReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readAllTeacher(): String

    /**
     * Get teachers under a specific department.
     * @param deptId the ID of the department.
     * @return on success return the JSON version of list of [TeacherReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readTeachersUnderDept(deptId: String): String

    /**
     * Read a teacher by their ID.
     * @param teacherId the ID of the teacher to read.
     * @return on success return the JSON version of the [TeacherReadEntity],
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readTeacherById(teacherId: String): String

    suspend fun updateFaculty(facultyId: String,json:String): String
    suspend fun updateDept(deptId: String,json:String): String
    suspend fun updateTeacher(teacherId: String,json:String): String


    /**@return on failure/success return the JSON version of [FeedbackMessageEntity]*/
    suspend fun  deleteTeacher(id: String):String

}
