@file:Suppress("unused")
package domain.service
import domain.entity.DepartmentReadEntity
import domain.entity.FacultyReadEntity
import domain.entity.TeacherReadEntity
interface ReadEntityParserService {

    /**
     * - Parse a [FacultyReadEntity] from the given JSON or throw an exception.
     * - It filters extra fields not present in [FacultyReadEntity] and returns the JSON version of the parsed entity.
     * @param json the raw JSON input to be parsed.
     * @return the parsed [FacultyReadEntity] in JSON format, ready to be sent to the client.
     * @throws Throwable if the JSON format is invalid or does not match [FacultyReadEntity].
     */
    fun parseAsFacultyOrThrow(json: String): String

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
