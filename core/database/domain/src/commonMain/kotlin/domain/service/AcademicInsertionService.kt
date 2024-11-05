@file:Suppress("unused")
package domain.service
import core.data.entity.academic.*
import domain.model.InsertionResult

/**
 * - Validate the json
 * - Add the missing fields and make updated json
 * - Decide the primary key
 * - Return the primary key and updated json
 *
 *
 * - Single place to create the primary key  as  a result database like mongo and firebase can directly insert
 * the json to the database, need not  to maintain a separate entity or model,as  a result implementing new database
 * requires less code so easily can switch between different databases
 *
 * - Database or any layer need not  to validate the entity separately because while generating primary key
 * it will validate the data,that means before inserting the data it will validated
 * - In case of mongo or firebase the primary key can be used as document id to make sure there is uniqueness
 * - Since entities are defined in this module  so make sense to define the validator here to maintain
 * single source of truth
 * - It add the missing fields here so any database directly will insert the json to the database without any
 * modifications this will ensure that implementing a new database with less code and maintain single source
 * of truth for inserting missing or extra fields

 */
interface AcademicInsertionService {

    /**
     * Generate primary key for faculty and validate the write entity.
     * @param json pass the [FacultyWriteEntity] in JSON format.
     * @return the generated primary key for the faculty.
     * @throws Throwable validation of the entity fails or the JSON format is invalid or doesn't match [FacultyWriteEntity].
     **/
    fun getFacultyKeyOrThrow(json: String): InsertionResult

    /**
     * Generate primary key for faculty and validate the write entity.
     * @param json pass the [FacultyWriteEntity] in JSON format.
     * @return the generated primary key for the faculty.
     * @throws Throwable validation of the entity fails or the JSON format is invalid or doesn't match [FacultyWriteEntity].
     **/
    fun getFacultyPrimaryKey(entity: FacultyWriteEntity): String



    /**
     * Generate primary key for department and validate the write entity.
     * - Need to know under which faculty wants to add, and id will be used to insert missing fields to json
     * @param json pass the [DepartmentWriteEntity] in JSON format.
     * @return the generated primary key for the department.
     * @throws Throwable validation of the entity fails or the JSON format is invalid or doesn't match [DepartmentWriteEntity].
     **/
    fun getDepartmentKeyOrThrow(json: String,facultyId:String): InsertionResult

    /**
     * Generate primary key for department and validate the write entity.
     * - Need to know under which faculty wants to add, and id will be used to insert missing fields to json
     * @param json pass the [DepartmentWriteEntity] in JSON format.
     * @return the generated primary key for the department.
     * @throws Throwable validation of the entity fails or the JSON format is invalid or doesn't match [DepartmentWriteEntity].
     **/
    fun getDeptPrimaryKey(entity: DepartmentWriteEntity, facultyId:String): String

    /**
     * Generate primary key for teacher and validate the write entity
     *  - Need to know under which dept wants to add, and id will be used to insert missing fields to json
     * @param json pass the [TeacherWriteEntity] in JSON format.
     * @return the generated primary key for the teacher.
     * @throws Throwable validation of the entity fails or the JSON format is invalid or doesn't match [TeacherWriteEntity].
     **/
    fun getTeacherKeyOrThrow(json: String,deptId:String): InsertionResult

    /**
     * Generate primary key for teacher and validate the write entity
     *  - Need to know under which dept wants to add, and id will be used to insert missing fields to json
     * @param json pass the [TeacherWriteEntity] in JSON format.
     * @return the generated primary key for the teacher.
     * @throws Throwable validation of the entity fails or the JSON format is invalid or doesn't match [TeacherWriteEntity].
     **/
    fun getTeacherPrimaryKey(entity: TeacherWriteEntity, deptId:String): String
}
