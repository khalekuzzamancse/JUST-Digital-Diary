@file:Suppress("unused")

package core.database.datasource.monggodb.db

import com.mongodb.client.model.Filters
import core.database.datasource.monggodb.db.MongoDBClient.COLLECTION_FACULTY
import core.database.datasource.monggodb.db.MongoDBClient.DATABASE_NAME
import core.database.datasource.monggodb.db.MongoDBClient.ID_FIELD
import core.database.datasource.monggodb.core.insertionWithHandleException
import domain.entity.academic.FacultyReadEntity
import domain.factory.ContractFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document

internal class FacultyCollection {

    private val insertionService = ContractFactory.insertionService()
    private val readEntityService = ContractFactory.academicReadEntityService()


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

            val result = insertionService.getFacultyKeyOrThrow(json)

            val doc = Document.parse(result.json)
                .append(ID_FIELD, result.primaryKey)

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
        return MongoDBClient.readOrThrow(DATABASE_NAME) { database ->
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
        return MongoDBClient.readOrThrow(DATABASE_NAME) { database ->
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
        data = json,
        query = Filters.eq(FacultyReadEntity::faculty_id.name, facultyId)
    )
}
