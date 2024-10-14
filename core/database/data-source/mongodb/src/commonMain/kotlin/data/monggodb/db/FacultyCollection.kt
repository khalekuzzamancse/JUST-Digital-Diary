@file:Suppress("unused")

package data.monggodb.db

import com.mongodb.client.model.Filters
import data.monggodb.db.MongoDBClient.COLLECTION_FACULTY
import data.monggodb.db.MongoDBClient.DATABASE_NAME
import data.monggodb.factory.insertionWithHandleException
import domain.entity.FacultyReadEntity
import domain.factory.ContractFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document

internal class FacultyCollection {

    private val primaryKeyService = ContractFactory.primaryKeyService()
    private val readEntityService = ContractFactory.readEntityParserService()

    private companion object {
        const val ID_KEY = "_id"
    }

    /**
     * Adds a faculty to the database.
     *
     * @param json The Faculty object in JSON format to be added.
     * @throws Throwable if the operation fails.
     */
    @Throws(Throwable::class)
    suspend fun insert(json: String) = insertionWithHandleException {
        MongoDBClient.writeOrThrow(DATABASE_NAME) { database ->
            val collection = database.getCollection<Document>(COLLECTION_FACULTY)

            val primaryKey = primaryKeyService.getFacultyKeyOrThrow(json)

            val doc = Document.parse(json)
                .append(ID_KEY, primaryKey)
                .append(FacultyReadEntity::faculty_id.name, primaryKey)

            collection.insertOne(doc)
        }
    }

    /**
     * Retrieves all faculties from the database and returns them as a JSON array.
     *
     * @return A JSON array string of all faculties.
     * @throws Throwable if the operation fails.
     */
    @Throws(Throwable::class)
    suspend fun getAllFaculties(): String {
        return MongoDBClient.readOrThrow(DATABASE_NAME, COLLECTION_FACULTY) { database ->
            val collection = database.getCollection<Document>(COLLECTION_FACULTY)

            val jsonArray = collection.find().toList().map { document ->
                //TODO:Later fetch the number of employees and refactor it
                val doc = document.append(FacultyReadEntity::number_of_dept.name, 0)
                readEntityService.parseAsFacultyOrThrow(doc.toJson())
            }

            // Join all the JSON strings into a single JSON array
            "[${jsonArray.joinToString(",")}]"
        }
    }

    /**
     * Reads a faculty by its ID and returns it as a JSON string.
     *
     * @param id The faculty ID.
     * @return The JSON string of the faculty.
     * @throws Throwable if the operation fails or the faculty is not found.
     */
    @Throws(Throwable::class)
    suspend fun read(id: String): String {
        return MongoDBClient.readOrThrow(DATABASE_NAME, COLLECTION_FACULTY) { database ->
            val collection = database.getCollection<Document>(COLLECTION_FACULTY)

            val document =
                collection.find(Document(FacultyReadEntity::faculty_id.name, id)).firstOrNull()
                    ?: throw NoSuchElementException("No Faculty found")
            //TODO:Later fetch the number of employees and refactor it
            val doc = document.append(FacultyReadEntity::number_of_dept.name, 0)
            // Parse and return the JSON string of the faculty
            readEntityService.parseAsFacultyOrThrow(doc.toJson())
        }
    }

    suspend fun updateOrThrow(facultyId: String, json: String) = MongoDBClient.updateOneOrThrow(
        databaseName = DATABASE_NAME,
        collectionName = COLLECTION_FACULTY,
        jsonUpdate = json,
        query = Filters.eq(FacultyReadEntity::faculty_id.name, facultyId)
    )
}
