@file:Suppress("unused")
package data.monggodb.db

import com.mongodb.client.model.Filters
import data.monggodb.db.MongoDBClient.COLLECTION_TEACHER
import data.monggodb.db.MongoDBClient.DATABASE_NAME
import data.monggodb.factory.insertionWithHandleException
import domain.entity.DepartmentReadEntity
import domain.entity.TeacherReadEntity
import domain.factory.ContractFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document

internal class TeacherCollection {

    private val primaryKeyService = ContractFactory.primaryKeyService()
    private val readEntityService = ContractFactory.readEntityParserService()

    private companion object {
        const val ID_KEY = "_id"
    }

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

            val primaryKey = primaryKeyService.getTeacherKeyOrThrow(json)

            val doc = Document.parse(json)
                .append(ID_KEY, primaryKey)//_id ,belong to document  id
                .append(TeacherReadEntity::dept_id.name, deptId)//dept_id
                .append(TeacherReadEntity::id.name,primaryKey)//id

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
        return MongoDBClient.readOrThrow(DATABASE_NAME, COLLECTION_TEACHER) { database ->
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
        return MongoDBClient.readOrThrow(DATABASE_NAME, COLLECTION_TEACHER) { database ->
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
        return MongoDBClient.readOrThrow(DATABASE_NAME, COLLECTION_TEACHER) { database ->
            val collection = database.getCollection<Document>(COLLECTION_TEACHER)

            //The document id is used as teacher id because it not explicitly store
            val document = collection.find(Document(ID_KEY, teacherId)).firstOrNull()
                ?: throw NoSuchElementException("No teacher found")
            readEntityService.parseAsTeacherOrThrow(document.toJson())
        }
    }
    suspend fun updateOrThrow(teacherId: String, json: String) = MongoDBClient.updateOneOrThrow(
        databaseName = DATABASE_NAME,
        collectionName = COLLECTION_TEACHER,
        jsonUpdate = json,
        query = Filters.eq(TeacherReadEntity::id.name, teacherId)
    )

}
