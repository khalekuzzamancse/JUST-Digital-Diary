@file:Suppress("unused")
package domain.service
import domain.entity.DepartmentWriteEntity
import domain.entity.FacultyWriteEntity
import domain.entity.TeacherWriteEntity
/**
 * - Single place to create the primary key  as  a result database like mongo and firebase can directly insert
 * the json to the database, need to maintain a separate entity or model,as  a result implementing new database
 * requires less code so easily can switch between different databases
 * - Database or any layer need not  to validate the entity separately because while generating primary key
 * it will validate the data,that means before inserting the data it will validated
 * - In case of mongo or firebase the primary key can be used as document id to make sure there is uniqueness
 * - Since entities are defined in this module  so make sense to define the validator here to maintain
 * single source of truth

 */
interface PrimaryKeyService {

    /**
     * Generate primary key for faculty and validate the write entity.
     * @param json pass the [FacultyWriteEntity] in JSON format.
     * @return the generated primary key for the faculty.
     * @throws Throwable validation of the entity fails or the JSON format is invalid or doesn't match [FacultyWriteEntity].
     **/
    fun getFacultyKeyOrThrow(json: String): String

    /**
     * Generate primary key for department and validate the write entity.
     * @param json pass the [DepartmentWriteEntity] in JSON format.
     * @return the generated primary key for the department.
     * @throws Throwable validation of the entity fails or the JSON format is invalid or doesn't match [DepartmentWriteEntity].
     **/
    fun getDepartmentKeyOrThrow(json: String): String

    /**
     * Generate primary key for teacher and validate the write entity.
     * @param json pass the [TeacherWriteEntity] in JSON format.
     * @return the generated primary key for the teacher.
     * @throws Throwable validation of the entity fails or the JSON format is invalid or doesn't match [TeacherWriteEntity].
     **/
    fun getTeacherKeyOrThrow(json: String): String
}
