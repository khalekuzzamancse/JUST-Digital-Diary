package core.database.monggodb.database

import com.mongodb.client.result.InsertOneResult
import core.database.monggodb.entity.FacultyAddEntity
import core.database.monggodb.entity.FacultyEntity
import core.database.monggodb.entity.MessageEntity
import core.database.monggodb.services.toFeedbackMessage
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

internal class FacultyDb {

    /**
     * Adds a faculty to the database.
     *
     * @param faculty The Faculty object to be added.
     * @throws Throwable if the operation fails.
     */
    @Throws(Throwable::class)
     suspend fun addFaculty(faculty: FacultyAddEntity): String {
        val json = Json { prettyPrint = true }
        return try {
            MongoDBClient.executeWriteOperation("JustDiary", "faculties") { database ->
                val collection = database.getCollection<Document>("faculties")
                val id=faculty.name.replace(" ","").lowercase()

                val facultyDoc = Document()
                    .append("_id", id)
                    .append("id", faculty.priority)
                    .append("faculty_id", id)
                    .append("name", faculty.name)


                val result: InsertOneResult = collection.insertOne(facultyDoc)

                if (result.insertedId != null) {
                     json.encodeToString(  MessageEntity("Faculty added successfully!"))

                } else {
                    json.encodeToString(   MessageEntity("Failed to add faculty. Please try again."))

                }
            }
        } catch (e: Exception) {

            toFeedbackMessage(e)
        }
    }


    /**
     * Retrieves all faculties from the database.
     *
     * @return A list of Faculty objects.
     * @throws Throwable if the operation fails.
     */
    @Throws(Throwable::class)
    suspend fun getAllFaculties(): List<FacultyEntity> {
        return MongoDBClient.executeReadOperation("JustDiary", "faculties") { database ->
            val collection = database.getCollection<Document>("faculties")
            collection.find().toList().map { document ->
                FacultyEntity(
                    priority = document.getInteger("id"),
                    faculty_id = document.getString("faculty_id"),
                    name = document.getString("name"),
                )
            }
        }
    }

}
