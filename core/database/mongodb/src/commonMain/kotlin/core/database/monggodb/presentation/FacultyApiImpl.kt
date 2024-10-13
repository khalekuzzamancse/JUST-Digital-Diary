package core.database.monggodb.presentation

import core.database.monggodb.data.database.FacultyCollection
import core.database.monggodb.data.services.toFeedback
import core.database.monggodb.di.DBFactory
import core.database.monggodb.domain.model.FacultyEntity
import core.database.monggodb.domain.model.FeedbackEntity
import kotlinx.serialization.encodeToString

class FacultyApiImpl internal constructor() : FacultyApi {
    private  val parser = DBFactory.jsonParser2()
    override suspend fun readAll(): String {
        try {
            val facultyList = FacultyCollection().getAllFaculties()
            return parser.encodeToString(facultyList)
        } catch (e: Exception) {
            return parser.encodeToString(FeedbackEntity("Error:$e"))
        }
    }

    override suspend fun insert(json: String): String {
        return try {
            val entity = DBFactory.jsonParser().parseOrThrow(json, FacultyEntity.serializer())
            FacultyCollection().addFaculty(entity)

        } catch (e: Throwable) {
            toFeedback(e)
        }

    }

    override suspend fun read(id: String): String {
        try {
            val entity = FacultyCollection().read(id)
            return parser.encodeToString(entity)
        } catch (e: Exception) {
            return parser.encodeToString(FeedbackEntity("Error:$e"))
        }
    }

}

