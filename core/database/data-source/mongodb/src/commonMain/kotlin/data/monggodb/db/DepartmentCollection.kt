@file:Suppress("unused")
package data.monggodb.db

import com.mongodb.client.model.Filters
import data.monggodb.db.MongoDBClient.COLLECTION_DEPARTMENT
import data.monggodb.db.MongoDBClient.DATABASE_NAME
import data.monggodb.factory.insertionWithHandleException
import domain.entity.DepartmentReadEntity
import domain.factory.ContractFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document

internal class DepartmentCollection {
    private val primaryKeyService = ContractFactory.primaryKeyService()
    private val readEntityService = ContractFactory.readEntityParserService()

    private companion object {
        const val ID_KEY = "_id"
    }

    suspend fun insert(facultyId: String, json: String) = insertionWithHandleException {
        MongoDBClient.writeOrThrow(DATABASE_NAME) { database ->
            val collection = database.getCollection<Document>(COLLECTION_DEPARTMENT)
            val pk = primaryKeyService.getDepartmentKeyOrThrow(json)
            val doc = Document.parse(json)
                .append(ID_KEY, pk)
                .append(DepartmentReadEntity::dept_id.name,pk)
                .append(DepartmentReadEntity::faculty_id.name,facultyId)
            collection.insertOne(doc)
        }

    }

    @Throws(Throwable::class)
    suspend fun readAll(): String {
        return MongoDBClient.writeOrThrow(
            DATABASE_NAME
        ) { database ->
            val collection = database.getCollection<Document>(COLLECTION_DEPARTMENT)

            val jsonArray = collection.find().toList().map { document ->
                //TODO:Later fetch the number of employees and refactor it
                val doc = document.append(DepartmentReadEntity::number_of_employee.name, 0)
                readEntityService.parseAsDeptOrThrow(doc.toJson())
            }
            // Join all the JSON strings into a single JSON array
            "[${jsonArray.joinToString(",")}]"
        }
    }

    @Throws(Throwable::class)
    suspend fun readUnderFaculty(facultyId: String): String {
        return MongoDBClient.readOrThrow(
            DATABASE_NAME,
            COLLECTION_DEPARTMENT
        ) { database ->
            val collection = database.getCollection<Document>(COLLECTION_DEPARTMENT)

            val jsonArray =
                collection.find(Filters.eq(DepartmentReadEntity::faculty_id.name, facultyId))
                    .toList().map { document ->
                    // TODO: Later fetch the number of employees and refactor it
                    val doc = document.append(DepartmentReadEntity::number_of_employee.name, 0)
                    readEntityService.parseAsDeptOrThrow(doc.toJson())
                }

            // Join all the JSON strings into a single JSON array
            "[${jsonArray.joinToString(",")}]"
        }
    }

    @Throws(Throwable::class)
    suspend fun readById(deptId: String): String {
        return MongoDBClient.readOrThrow(
            DATABASE_NAME,
            COLLECTION_DEPARTMENT
        ) { database ->
            val collection = database.getCollection<Document>(COLLECTION_DEPARTMENT)

            val document =
                collection.find(Document(DepartmentReadEntity::dept_id.name, deptId)).firstOrNull()
                    ?: throw NoSuchElementException("No department found ")

            // TODO: Later fetch the number of employees and refactor it
            val doc = document.append(DepartmentReadEntity::number_of_employee.name, 0)

            // Parse and return the JSON string of the department
            readEntityService.parseAsDeptOrThrow(doc.toJson())
        }
    }
    suspend fun updateOrThrow(deptId: String, json: String) = MongoDBClient.updateOneOrThrow(
        databaseName = DATABASE_NAME,
        collectionName = COLLECTION_DEPARTMENT,
        jsonUpdate = json,
        query = Filters.eq(DepartmentReadEntity::dept_id.name, deptId)
    )

}
