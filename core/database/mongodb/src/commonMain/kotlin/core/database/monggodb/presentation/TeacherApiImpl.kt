package core.database.monggodb.presentation

import core.database.monggodb.data.database.TeacherCollection
import core.database.monggodb.domain.model.FeedbackEntity
import core.database.monggodb.domain.model.TeacherEntity
import core.database.monggodb.data.services.toFeedback
import core.database.monggodb.di.DBFactory
import kotlinx.serialization.encodeToString

class TeacherApiImpl: TeacherApi {
    private  val parser = DBFactory.jsonParser2()
    override suspend fun insert(deptId: String,json: String): String {

        return try {
            val entity = DBFactory.jsonParser().parseOrThrow(json, TeacherEntity.serializer())
            TeacherCollection().add(deptId,entity)
        } catch (e: Throwable) {
            toFeedback(e)
        }

    }

    override suspend fun readAll(): String {
        try {
            val response = TeacherCollection().read()
            return parser.encodeToString(response)

        } catch (e: Exception) {
            return parser.encodeToString(FeedbackEntity("Error:$e"))
        }
    }

    override suspend fun readUnderDept(deptId: String): String {
        try {
            val response = TeacherCollection().read(deptId)
            return parser.encodeToString(response)

        } catch (e: Exception) {
            return parser.encodeToString(FeedbackEntity("Error:$e"))
        }
    }

    override suspend fun readById(teacherId: String): String {
        try {
            val response = TeacherCollection().readById(teacherId)
            return parser.encodeToString(response)

        } catch (e: Exception) {
            return parser.encodeToString(FeedbackEntity("Error:$e"))
        }
    }
}