@file:Suppress("unused")
package core.database.datasource.monggodb.db

import com.mongodb.client.model.Filters
import core.database.datasource.monggodb.db.MongoDBClient.COLLECTION_TEACHER
import core.database.datasource.monggodb.db.MongoDBClient.DATABASE_NAME
import core.database.datasource.monggodb.db.MongoDBClient.ID_FIELD
import core.database.datasource.monggodb.core.insertionWithHandleException
import domain.entity.academic.TeacherReadEntity
import domain.factory.ContractFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document

internal class TeacherCollection {

    private val insertionService = ContractFactory.insertionService()
    private val readEntityService = ContractFactory.academicReadEntityService()


    /**
     * Adds a teacher to the database.
     *
     * @param deptId The department ID under which the teacher is added.
     * @param json The Teacher object in JSON format to be added.
     * @throws Throwable if the operation fails.
     */
    @Throws(Throwable::class)
    suspend fun insert(deptId: String, json: String) = insertionWithHandleException {
        MongoDBClient.writeOrThrow(DATABASE_NAME) { database ->
            val collection = database.getCollection<Document>(COLLECTION_TEACHER)

            val result = insertionService.getTeacherKeyOrThrow(json, deptId)

            val doc = Document.parse(result.json)
                .append(ID_FIELD, result.primaryKey)//_id ,belong to document  id

            collection.insertOne(doc)
        }
    }

    /**
     * Retrieves all teachers from the database and returns them as a JSON array.
     *
     * @return A JSON array string of all teachers.
     * @throws Throwable if the operation fails.
     */
    @Throws(Throwable::class)
    suspend fun readAll(): String {
        return MongoDBClient.readOrThrow(DATABASE_NAME) { database ->
            val collection = database.getCollection<Document>(COLLECTION_TEACHER)

            val jsonArray = collection.find().toList().map { document ->

                readEntityService.parseAsTeacherOrThrow(document.toJson())
            }

            "[${jsonArray.joinToString(",")}]"
        }
    }

    /**
     * Reads a teacher by department ID and returns them as a JSON array.
     *
     * @param deptId The department ID.
     * @return A JSON array string of all teachers under the department.
     * @throws Throwable if the operation fails.
     */
    @Throws(Throwable::class)
    suspend fun readByDeptId(deptId: String): String {
        return MongoDBClient.readOrThrow(DATABASE_NAME) { database ->
            val collection = database.getCollection<Document>(COLLECTION_TEACHER)

            val jsonArray =
                collection.find(Filters.eq(TeacherReadEntity::dept_id.name, deptId)).toList()
                    .map { document ->
                        readEntityService.parseAsTeacherOrThrow(document.toJson())
                    }

            "[${jsonArray.joinToString(",")}]"
        }
    }

    /**
     * Reads a teacher by their ID and returns it as a JSON string.
     *
     * @param teacherId The teacher ID.
     * @return The JSON string of the teacher.
     * @throws Throwable if the operation fails or the teacher is not found.
     */
    @Throws(Throwable::class)
    suspend fun readById(teacherId: String): String {
        return MongoDBClient.readOrThrow(DATABASE_NAME) { database ->
            val collection = database.getCollection<Document>(COLLECTION_TEACHER)

            //The document id is used as teacher id because it not explicitly store
            val document = collection.find(Document(ID_FIELD, teacherId)).firstOrNull()
                ?: throw NoSuchElementException("No teacher found")
            readEntityService.parseAsTeacherOrThrow(document.toJson())
        }
    }
    suspend fun updateOrThrow(teacherId: String, json: String) = MongoDBClient.updateOneOrThrow(
        databaseName = DATABASE_NAME,
        collectionName = COLLECTION_TEACHER,
        data = json,
        query = Filters.eq(TeacherReadEntity::id.name, teacherId)
    )

}
