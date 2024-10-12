package core.database.monggodb.presentation.apis

import core.database.monggodb.database.FacultyDb
import core.database.monggodb.entity.FacultyAddEntity
import core.database.monggodb.entity.MessageEntity
import core.database.monggodb.presentation.DBFactory
import core.database.monggodb.presentation.model.FacultyRetrieveModel
import core.database.monggodb.services.toFeedbackMessage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FacultyApiImpl internal constructor() : FacultyApi {
    override suspend fun getAllFaculties(): String {
        val json = Json { prettyPrint = true }
        try {
            val facultyList = FacultyDb().getAllFaculties().map { entity ->
                FacultyRetrieveModel(
                    priority = entity.priority,
                    facultyId = entity.faculty_id,
                    name = entity.name,
                    departmentCount = 0
                )
            }

            return json.encodeToString(facultyList)
        } catch (e: Exception) {
            return json.encodeToString(MessageEntity("Error:$e"))
        }
    }

    override suspend fun addFaculty(json: String): String {
        return try {
            val entity = DBFactory.jsonParser().parseOrThrow(json, FacultyAddEntity.serializer())
            FacultyDb().addFaculty(entity)

        } catch (e: Throwable) {
            toFeedbackMessage(e)
        }

    }

}

