@file:Suppress("unused")

package domain.service
import core.data.entity.academic.*

interface AcademicReadEntityService {

    /**
     * - Parse a [FacultyReadEntity] from the given JSON or throw an exception.
     * - It filters extra fields not present in [FacultyReadEntity] and returns the JSON version of the parsed entity.
     * @param json the raw JSON input to be parsed.
     * @return the parsed [FacultyReadEntity] in JSON format, ready to be sent to the client.
     * @throws Throwable if the JSON format is invalid or does not match [FacultyReadEntity].
     */
    fun parseAsFacultyOrThrow(json: String): String
    fun parseAsFacultyListOrThrow(json: String): List<FacultyReadEntity>
    fun parseAsDeptReadEntitiesOrThrow(json: String): List<DepartmentReadEntity>
    fun isFacultyListReadEntity(json: String):Boolean
    fun isDeptListReadEntity(json: String):Boolean
    fun isTeacherListReadEntity(json: String):Boolean
    fun parseAsTeacherReadEntitiesOrThrow(json: String):List<TeacherReadEntity>

    fun toJsonOrThrow(teachers: List<TeacherReadEntity>):String

    /**
     * - It possible that some data source need to parse  entity to json before sending the result as json
     * - Example as Room database return Class as Entity but this module accept the Json format so need to convert the Json
     * that is maintaining single source of truth so that any data source can be it
     */
    fun parseFromDeptListOrThrow(entity: FacultyReadEntity): String

    /**
     * - It possible that some data source need to parse  entity to json before sending the result as json
     * - Example as Room database return Class as Entity but this module accept the Json format so need to convert the Json
     * that is maintaining single source of truth so that any data source can be it
     */
    fun parseAsFacultyListOrThrow(faculties: List<FacultyReadEntity>): String
    fun parseFromDeptListOrThrow(departments: List<DepartmentReadEntity>): String

    /**
     * - Parse a [DepartmentReadEntity] from the given JSON or throw an exception.
     * - It filters extra fields not present in [DepartmentReadEntity] and returns the JSON version of the parsed entity.
     * @param json the raw JSON input to be parsed.
     * @return the parsed [DepartmentReadEntity] in JSON format, ready to be sent to the client.
     * @throws Throwable if the JSON format is invalid or does not match [DepartmentReadEntity].
     */
    fun parseAsDeptOrThrow(json: String): String

    /**
     * - Parse a [TeacherReadEntity] from the given JSON or throw an exception.
     * - It filters extra fields not present in [TeacherReadEntity] and returns the JSON version of the parsed entity.
     * @param json the raw JSON input to be parsed.
     * @return the parsed [TeacherReadEntity] in JSON format, ready to be sent to the client.
     * @throws Throwable if the JSON format is invalid or does not match [TeacherReadEntity].
     */
    fun parseAsTeacherOrThrow(json: String): String
}
