package core.database.monggodb.database

import com.mongodb.client.result.InsertOneResult
import core.database.monggodb.database.DepartmentDb.Companion
import core.database.monggodb.database.DepartmentDb.Companion.SHORTNAME_KEY
import core.database.monggodb.entity.DepartmentEntity
import core.database.monggodb.entity.DepartmentEntryEntity
import core.database.monggodb.entity.MessageEntity
import core.database.monggodb.entity.TeacherEntryEntity
import core.database.monggodb.entity.TeacherResponseEntity
import core.database.monggodb.services.toFeedbackMessage
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document
import java.util.UUID

internal class TeacherDb {

    companion object {
        const val ID_KEY = "_id"
        const val DEPT_ID_KEY = "dept_id"
        const val PRIORITY_KEY = "priority"
        const val NAME_KEY = "name"
        const val EMAIL_KEY = "email"
        const val PHONE_KEY = "phone"
        const val ADDITIONAL_EMAIL_KEY = "additional_email"
        const val ACHIEVEMENTS_KEY = "achievements"
        const val DESIGNATIONS_KEY = "designations"
        const val ROOM_NO_KEY = "room_no"
    }

    suspend fun add(entity: TeacherEntryEntity): String {
        val json = Json { prettyPrint = true }
        return try {
            MongoDBClient.executeWriteOperation("JustDiary", "teachers") { database ->
                val collection = database.getCollection<Document>("teachers")

                /**
                 * - each no two teacher has the same name+same email
                 * - TODO:Refactor it later because a teacher may change his email
                 */
                val primaryKey="${entity.name}_${entity.email}"
                val teacherDoc = Document()
                    .append(ID_KEY,primaryKey )
                    .append(DEPT_ID_KEY, entity.deptId)
                    .append(PRIORITY_KEY, entity.priority)
                    .append(NAME_KEY, entity.name)
                    .append(EMAIL_KEY, entity.email)
                    .append(PHONE_KEY, entity.phone)
                    .append(ADDITIONAL_EMAIL_KEY, entity.additionalEmail)
                    .append(ACHIEVEMENTS_KEY, entity.achievements)
                    .append(DESIGNATIONS_KEY, entity.designations)
                    .append(ROOM_NO_KEY, entity.roomNo)

                val result: InsertOneResult = collection.insertOne(teacherDoc)

                if (result.insertedId != null) {
                    json.encodeToString(MessageEntity("Added successfully!"))
                } else {
                    json.encodeToString(MessageEntity("Server Error: Failed to add"))
                }
            }
        } catch (e: Exception) {
            toFeedbackMessage(e)
        }
    }

    @Throws(Throwable::class)
    suspend fun read(): List<TeacherResponseEntity> {
        return MongoDBClient.executeReadOperation("JustDiary", "teachers") { database ->
            val collection = database.getCollection<Document>("teachers")
            collection.find().toList().map { document ->
                TeacherResponseEntity(
                    priority = document.getInteger(PRIORITY_KEY),
                    id = document.getString(ID_KEY),
                    name = document.getString(NAME_KEY),
                    email = document.getString(EMAIL_KEY),
                    phone = document.getString(PHONE_KEY),
                    achievements = document.getString(ACHIEVEMENTS_KEY),
                    additionalEmail = document.getString(ADDITIONAL_EMAIL_KEY),
                    designations = document.getString(DESIGNATIONS_KEY),
                    roomNo = document.getString(ROOM_NO_KEY)
                )
            }
        }
    }
}
