package core.database.monggodb.presentation.apis

import core.database.monggodb.database.TeacherDb
import core.database.monggodb.entity.MessageEntity
import core.database.monggodb.entity.TeacherEntryEntity
import core.database.monggodb.presentation.DBFactory
import core.database.monggodb.services.toFeedbackMessage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TeacherApiImpl:TeacherApi {
    override suspend fun add(json: String): String {
        return try {
            val entity = DBFactory.jsonParser().parseOrThrow(json, TeacherEntryEntity.serializer())
            TeacherDb().add(entity)
        } catch (e: Throwable) {
            toFeedbackMessage(e)
        }

    }

    override suspend fun read(): String {
        val json = Json { prettyPrint = true }
        try {
            val response = TeacherDb().read()
            return json.encodeToString(response)

        } catch (e: Exception) {
            return json.encodeToString(MessageEntity("Error:$e"))
        }
    }
}