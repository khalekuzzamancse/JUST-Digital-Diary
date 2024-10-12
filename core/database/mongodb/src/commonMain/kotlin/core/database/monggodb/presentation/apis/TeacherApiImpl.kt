package core.database.monggodb.presentation.apis

import core.database.monggodb.database.TeacherDb
import core.database.monggodb.entity.TeacherEntryEntity
import core.database.monggodb.presentation.DBFactory
import core.database.monggodb.services.toFeedbackMessage

class TeacherApiImpl:TeacherApi {
    override suspend fun add(json: String): String {
        return try {
            val entity = DBFactory.jsonParser().parseOrThrow(json, TeacherEntryEntity.serializer())
            TeacherDb().add(entity)
        } catch (e: Throwable) {
            toFeedbackMessage(e)
        }

    }
}