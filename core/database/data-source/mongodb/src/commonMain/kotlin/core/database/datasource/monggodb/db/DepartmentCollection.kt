@file:Suppress("unused")
package core.database.datasource.monggodb.db

import com.mongodb.client.model.Filters
import core.database.datasource.monggodb.db.MongoDBClient.COLLECTION_DEPARTMENT
import core.database.datasource.monggodb.db.MongoDBClient.ID_FIELD
import core.database.datasource.monggodb.core.insertionWithHandleException
import core.data.entity.academic.*
import domain.factory.ContractFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document

internal class DepartmentCollection {
    private val insertionService = ContractFactory.insertionService()
    private val readEntityService = ContractFactory.academicReadEntityService()
    private val collection=COLLECTION_DEPARTMENT


    suspend fun insert(facultyId: String, json: String) = insertionWithHandleException {
        MongoDBClient.writeOrThrow { database ->
            val collection = database.getCollection<Document>(collection)
            val result = insertionService.getDepartmentKeyOrThrow(
                json = json,
                facultyId = facultyId
            )
            val doc = Document.parse(result.json)
                .append(ID_FIELD, result.primaryKey)
            collection.insertOne(doc)
        }

    }

    @Throws(Throwable::class)
    suspend fun readAll(): String {
        return MongoDBClient.writeOrThrow { database ->
            val collection = database.getCollection<Document>(collection)

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
        return MongoDBClient.readOrThrow { database ->
            val collection = database.getCollection<Document>(collection)

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
        return MongoDBClient.readOrThrow { database ->
            val collection = database.getCollection<Document>(collection)

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
        collectionName = collection,
        query = Filters.eq(DepartmentReadEntity::dept_id.name, deptId),
        data = json
    )
    suspend  fun deleteOrThrow(id: String) = MongoDBClient.deleteOneOrThrow(
        collectionName = collection,
        query = Filters.eq(DepartmentReadEntity::dept_id.name, id)
    )

}
