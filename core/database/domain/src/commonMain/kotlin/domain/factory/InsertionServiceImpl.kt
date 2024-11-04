@file:Suppress("unused", "functionName")

package domain.factory

import core.data.entity.academic.*
import domain.model.InsertionResult
import domain.service.AcademicInsertionService
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject

/**
 * - To know read [AcademicInsertionService] docs
 */
class InsertionServiceImpl internal constructor() : AcademicInsertionService {

    /**
     * Generate primary key for faculty and validate the write entity.
     * @param json pass the [FacultyWriteEntity] in JSON format.
     * @return the generated primary key for the faculty.
     **/
    override fun getFacultyKeyOrThrow(json: String): InsertionResult {
        val faculty = _parser().parseOrThrow(json, FacultyWriteEntity.serializer())
        //Execution is here means parse successful so it a valid json
        // Sure that no two faculty have the same name, so it can be used as the primary key
        val pk = faculty.name.replace(" ", "").lowercase()

        /**
         * [FacultyWriteEntity] does have the field `faculty_id` but the [FacultyReadEntity] have,
         * so we have to add the missing field here, otherwise causes exception while reading,
         * Since client does not have permission to to decide the `faculty_id` that is why deciding here
         */
        val extraFields = listOf(
            FacultyReadEntity::faculty_id.name to pk //keeping pk and faculty id are the same
        )

        return _toInsertionResult(json = json, fields = extraFields, primaryKey = pk)
    }


    /**
     * Generate primary key for department and validate the write entity.
     * @param json pass the [DepartmentWriteEntity] in JSON format.
     * @return the generated primary key for the department.
     **/
    override fun getDepartmentKeyOrThrow(json: String, facultyId: String): InsertionResult {
        val dept = _parser().parseOrThrow(json, DepartmentWriteEntity.serializer())
        // Sure that no two departments have the same name, so it can be used as the primary key
        val pk = dept.name.replace(" ", "").lowercase()


        /**
        client does not have permission to to decide the `dept_id` so this is missing, need to add
        Client  will decide the faculty id means under which faculty wants to insert but we did not
        take the faculty_id via the [DepartmentWriteEntity] so need to add this missing field
         */


        val extraFields = listOf(
            DepartmentReadEntity::dept_id.name to pk, //keeping pk and `dept_id` are the same
            DepartmentReadEntity::faculty_id.name to facultyId
        )

        return _toInsertionResult(json = json, fields = extraFields, primaryKey = pk)
    }

    /**
     * Generate primary key for teacher and validate the write entity.
     * @param json pass the [TeacherWriteEntity] in JSON format.
     * @return the generated primary key for the teacher.
     **/
    override fun getTeacherKeyOrThrow(json: String, deptId: String): InsertionResult {
        val teacher = _parser().parseOrThrow(json, TeacherWriteEntity.serializer())
        // Name+email combination is unique
        val pk =
            teacher.name.replace(" ", "").lowercase() + teacher.email.replace(" ", "").lowercase()
                //TODO: Email might be changed, so refactor this later

                /**
                 * - Client does not have permission to to decide the teacher`id` so this is missing, need to add
                 *  - Client  will decide the dept id means under which dept wants to insert but we did not
                 *   take the dept_id via the [TeacherWriteEntity] so need to add this missing field
                 */
        val extraFields = listOf(
            TeacherReadEntity::id.name to pk, //keeping pk and teacher `id` are the same
            TeacherReadEntity::dept_id.name to deptId
        )
        return _toInsertionResult(json = json, fields = extraFields, primaryKey = pk)
    }


    //TODO:Helper methods section

    private fun _parser() = ContractFactory.jsonParser()
    private fun _toInsertionResult(
        json: String,
        fields: List<Pair<String, String>>,
        primaryKey: String
    ): InsertionResult {
        var jsonObject = Json.parseToJsonElement(json).jsonObject
        for ((field, value) in fields) {
            jsonObject = JsonObject(jsonObject + (field to JsonPrimitive(value)))
        }
        val updatedJson = jsonObject.toString()
        return InsertionResult(primaryKey = primaryKey, json = updatedJson)
    }

}
