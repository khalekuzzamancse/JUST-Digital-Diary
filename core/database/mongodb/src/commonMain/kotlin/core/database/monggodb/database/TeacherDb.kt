package core.database.monggodb.database

import com.mongodb.client.result.InsertOneResult
import core.database.monggodb.entity.DepartmentEntryEntity
import core.database.monggodb.entity.MessageEntity
import core.database.monggodb.entity.TeacherEntryEntity
import core.database.monggodb.services.toFeedbackMessage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document
import java.util.UUID

internal class TeacherDb {

    suspend fun add(entity: TeacherEntryEntity): String {
        val json = Json { prettyPrint = true }
        return try {
            MongoDBClient.executeWriteOperation("JustDiary", "teachers") { database ->
                val collection = database.getCollection<Document>("teachers")

                val facultyDoc = Document()
                    .append("_id", UUID.randomUUID().toString())
                    .append("dept_id", entity.deptId)
                    .append("priority", entity.priority)
                    .append("name", entity.name)
                    .append("email", entity.email)
                    .append("phone", entity.phone)
                    .append("additional_email", entity.additionalEmail)
                    .append("achievements", entity.achievements)
                    .append("designations", entity.designations)
                    .append("room_no", entity.roomNo)

                val result: InsertOneResult = collection.insertOne(facultyDoc)

                if (result.insertedId != null) {
                    json.encodeToString(MessageEntity("Added successfully!"))

                } else {
                    json.encodeToString(MessageEntity("Server Error:Failed to add"))

                }
            }
        } catch (e: Exception) {

            toFeedbackMessage(e)
        }
    }

}
