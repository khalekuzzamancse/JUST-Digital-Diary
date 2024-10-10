package backend.database

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.coroutines.flow.toList
import org.bson.Document

class FacultyRepository {

    companion object {
        /**
         * Faculty data class representing a faculty entity.
         */
        data class Faculty(
            val id: Int,
            val faculty_id: String,
            val name: String,
            val department_count: Int
        )

        /**
         * Adds a faculty to the database.
         *
         * @param faculty The Faculty object to be added.
         * @throws Throwable if the operation fails.
         */
        @Throws(Throwable::class)
        suspend fun addFaculty(faculty: Faculty) {
            MongoDBClient.executeWriteOperation("JustDiary", "faculties") { database ->
                val collection = database.getCollection<Document>("faculties")
                val facultyDoc = Document()
                    .append("_id", faculty.faculty_id)
                    .append("id", faculty.id)
                    .append("faculty_id", faculty.faculty_id)
                    .append("name", faculty.name)
                    .append("department_count", faculty.department_count)

                collection.insertOne(facultyDoc)
            }
        }

        /**
         * Retrieves all faculties from the database.
         *
         * @return A list of Faculty objects.
         * @throws Throwable if the operation fails.
         */
        @Throws(Throwable::class)
        suspend fun getAllFaculties(): List<Faculty> {
            return MongoDBClient.executeReadOperation("JustDiary", "faculties") { database ->
                val collection = database.getCollection<Document>("faculties")
                collection.find().toList().map { document ->
                    Faculty(
                        id = document.getInteger("id"),
                        faculty_id = document.getString("faculty_id"),
                        name = document.getString("name"),
                        department_count = document.getInteger("department_count")
                    )
                }
            }
        }
    }
}
