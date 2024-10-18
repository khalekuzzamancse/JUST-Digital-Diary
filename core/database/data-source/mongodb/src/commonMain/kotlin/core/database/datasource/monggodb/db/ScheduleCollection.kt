@file:Suppress("unused", "functionName")

package core.database.datasource.monggodb.db

import com.mongodb.client.model.Filters
import core.database.datasource.monggodb.db.MongoDBClient.COLLECTION_SCHEDULE
import core.database.datasource.monggodb.db.MongoDBClient.ID_FIELD
import domain.core.EntityExtraField
import domain.entity.academic.DepartmentReadEntity
import domain.entity.schedule.ClassScheduleReadEntity
import domain.factory.ContractFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.json.Json
import org.bson.Document

internal class ScheduleCollection {
    private val scheduleService = ContractFactory.scheduleService()
    private val feedbackService = ContractFactory.feedbackService()
    private val academyService = ContractFactory.academicReadEntityService()
    private val collection = COLLECTION_SCHEDULE
    private val parser = Json { ignoreUnknownKeys = true }

    suspend fun insertOrThrow(deptId: String, json: String): String {
        val result =
            scheduleService.processAsClassScheduleWriteEntityOrThrow(json = json, deptId = deptId)
        val doc = Document.parse(result.json)
            .append(ID_FIELD, result.primaryKey)
        return MongoDBClient.insertOne(collectionName = collection, doc)
    }

    /**Useful for admin to read all and delete the old once*/
    @Throws(Throwable::class)
    suspend fun readAllOrThrow(): String {
        return MongoDBClient.writeOrThrow { database ->
            val collection = database.getCollection<Document>(collection)
            val jsonArray = collection.find().toList().map { document ->

                scheduleService.parseAsClassScheduleReadEntityOrThrow(_appendDeptInfo(document).toJson())
            }
            // Join all the JSON strings into a single JSON array
            "[${jsonArray.joinToString(",")}]"
        }
    }


    @Throws(Throwable::class)
    suspend fun readByDeptOrThrow(deptId: String): String {
        return MongoDBClient.readOrThrow { database ->
            val collection = database.getCollection<Document>(collection)
            val document =
                collection.find(Document(EntityExtraField.SCHEDULE_ENTITY_FIELD_DEPT_ID, deptId))
                    .firstOrNull()
                    ?: throw NoSuchElementException("No schedule available")
            scheduleService.parseAsClassScheduleReadEntityOrThrow(_appendDeptInfo(document).toJson())
        }
    }

    suspend fun updateOrThrow(id: String, json: String) = MongoDBClient.updateOneOrThrow(
        collectionName = collection,
        query = Filters.eq(ClassScheduleReadEntity::id.name, id),
        data = json
    )

    private suspend fun _appendDeptInfo(document: Document): Document {
        val deptId = document.getString(EntityExtraField.SCHEDULE_ENTITY_FIELD_DEPT_ID)
        val deptJson = DepartmentCollection().readById(deptId = deptId)
        val dept = parser.decodeFromString<DepartmentReadEntity>(deptJson)
        return document
            .append(ClassScheduleReadEntity::deptName.name, dept.name)
            .append(ClassScheduleReadEntity::deptShortName.name, dept.shortname)
    }

}
