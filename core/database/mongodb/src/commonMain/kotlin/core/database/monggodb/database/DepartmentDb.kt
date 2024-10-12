package core.database.monggodb.database

import com.mongodb.client.result.InsertOneResult
import core.database.monggodb.entity.DepartmentEntity
import core.database.monggodb.entity.DepartmentEntryEntity
import core.database.monggodb.entity.MessageEntity
import core.database.monggodb.services.toFeedbackMessage
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

internal class DepartmentDb {

    companion object {
        const val ID_KEY = "_id"
        const val FACULTY_ID_KEY = "faculty_id"
        const val DEPT_ID_KEY = "dept_id"
        const val PRIORITY_KEY = "priority"
        const val NAME_KEY = "name"
        const val SHORTNAME_KEY = "shortname"
    }

    suspend fun create(entity: DepartmentEntryEntity): String {
        val json = Json { prettyPrint = true }
        return try {
            MongoDBClient.executeWriteOperation("JustDiary", "departments") { database ->
                val collection = database.getCollection<Document>("departments")

                val id = entity.name.replace(" ", "").lowercase()

                val facultyDoc = Document()
                    .append(ID_KEY, id)
                    .append(FACULTY_ID_KEY, entity.facultyId)
                    .append(DEPT_ID_KEY, id)
                    .append(PRIORITY_KEY, entity.priority)
                    .append(NAME_KEY, entity.name)
                    .append(SHORTNAME_KEY, entity.shortName)

                val result: InsertOneResult = collection.insertOne(facultyDoc)

                if (result.insertedId != null) {
                    json.encodeToString(MessageEntity("added successfully!"))
                } else {
                    json.encodeToString(MessageEntity("Server Error: Failed to add"))
                }
            }
        } catch (e: Exception) {
            toFeedbackMessage(e)
        }
    }

    @Throws(Throwable::class)
    suspend fun read(): List<DepartmentEntity> {
        return MongoDBClient.executeReadOperation("JustDiary", "departments") { database ->
            val collection = database.getCollection<Document>("departments")
            collection.find().toList().map { document ->
                DepartmentEntity(
                    priority = document.getInteger(PRIORITY_KEY),
                    deptId = document.getString(DEPT_ID_KEY),
                    name = document.getString(NAME_KEY),
                    shortName = document.getString(SHORTNAME_KEY),
                )
            }
        }
    }
}
