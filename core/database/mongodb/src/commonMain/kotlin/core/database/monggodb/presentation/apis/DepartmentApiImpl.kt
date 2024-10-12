package core.database.monggodb.presentation.apis

import core.database.monggodb.database.DepartmentDb
import core.database.monggodb.entity.DepartmentEntryEntity
import core.database.monggodb.entity.MessageEntity
import core.database.monggodb.presentation.DBFactory
import core.database.monggodb.services.toFeedbackMessage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DepartmentApiImpl internal  constructor():DepartmentApi{
    override suspend fun add(json: String): String {
        return try {
            val entity = DBFactory.jsonParser().parseOrThrow(json, DepartmentEntryEntity.serializer())
            DepartmentDb().create(entity)
        } catch (e: Throwable) {
            toFeedbackMessage(e)
        }

    }

    override suspend fun read(): String {
        val json = Json { prettyPrint = true }
        try {
            val response = DepartmentDb().read()
            return json.encodeToString(response)

        } catch (e: Exception) {
            return json.encodeToString(MessageEntity("Error:$e"))
        }
    }
}