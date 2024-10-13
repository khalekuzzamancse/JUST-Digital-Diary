package core.database.monggodb.presentation

import core.database.monggodb.data.database.DepartmentCollection
import core.database.monggodb.domain.model.DepartmentEntity
import core.database.monggodb.domain.model.FeedbackEntity
import core.database.monggodb.data.services.toFeedback
import core.database.monggodb.di.DBFactory
import kotlinx.serialization.encodeToString

class DepartmentApiImpl internal  constructor(): DepartmentApi {
    private  val parser = DBFactory.jsonParser2()
    override suspend fun insert(facultyId:String,json: String): String {
        return try {
            val entity = DBFactory.jsonParser().parseOrThrow(json, DepartmentEntity.serializer())
            DepartmentCollection().create(facultyId,entity)
        } catch (e: Throwable) {
            toFeedback(e)
        }


    }

    override suspend fun read(): String {
        try {
            val response = DepartmentCollection().readAll()
            return parser.encodeToString(response)
        } catch (e: Exception) {
            return parser.encodeToString(FeedbackEntity("Error:$e"))
        }
    }

    override suspend fun readUnderFaculty(facultyId: String): String {

        try {
            val response = DepartmentCollection().readUnderFaculty(facultyId)
            return parser.encodeToString(response)
        } catch (e: Exception) {
            return parser.encodeToString(FeedbackEntity("Error:$e"))
        }
    }

    override suspend fun readById(id: String): String {
        try {
            val response = DepartmentCollection().readById(id)
            return parser.encodeToString(response)
        } catch (e: Exception) {
            return parser.encodeToString(FeedbackEntity("Error:$e"))
        }
    }
}