@file:Suppress("unused")
package domain.service
import domain.entity.FacultyWriteEntity
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
interface CalenderInsertionService {
    /**
     * Generate primary key for faculty and validate the write entity.
     * @param json pass the [FacultyWriteEntity] in JSON format.
     * @return the generated primary key for the faculty.
     * @throws Throwable validation of the entity fails or the JSON format is invalid or doesn't match [FacultyWriteEntity].
     **/
    fun processWriteEntityOrThrow(json: String): InsertionResult

}
