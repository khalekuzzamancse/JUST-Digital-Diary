@file:Suppress("unused")

package data.monggodb.db

import com.mongodb.client.model.Filters
import data.monggodb.db.MongoDBClient.COLLECTION_SCHEDULE
import data.monggodb.db.MongoDBClient.DATABASE_NAME
import data.monggodb.db.MongoDBClient.ID_FIELD
import domain.core.EntityExtraField
import domain.entity.calender.AcademicCalenderEntity
import domain.entity.schedule.ClassScheduleReadEntity
import domain.factory.ContractFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document

internal class ScheduleCollection {
    private val scheduleService = ContractFactory.scheduleService()
    private val feedbackService = ContractFactory.feedbackService()
    private val collection = COLLECTION_SCHEDULE

    suspend fun insert(deptId: String, json: String): String {
        val result =
            scheduleService.processAsClassScheduleWriteEntityOrThrow(json = json, deptId = deptId)
        val doc = Document.parse(result.json)
            .append(ID_FIELD, result.primaryKey)
        return MongoDBClient.insertOne(collectionName = collection, doc)
    }

    /**Useful for admin to read all and delete the old once*/
    @Throws(Throwable::class)
    suspend fun readAll(): String {
        return MongoDBClient.writeOrThrow(
            DATABASE_NAME
        ) { database ->
            val collection = database.getCollection<Document>(collection)
            val jsonArray = collection.find().toList().map { document ->
                //TODO: read the dept and fill the filed later
                val doc = document
                    .append(ClassScheduleReadEntity::deptName.name, "Not Implemented Yet")
                    .append(ClassScheduleReadEntity::deptShortName.name, "NIY")
                scheduleService.parseAsClassScheduleReadEntityOrThrow(doc.toJson())
            }
            // Join all the JSON strings into a single JSON array
            "[${jsonArray.joinToString(",")}]"
        }
    }

    @Throws(Throwable::class)
    suspend fun readByDept(deptId: String): String {
        return MongoDBClient.readOrThrow(
            DATABASE_NAME
        ) { database ->
            val collection = database.getCollection<Document>(collection)
            val document =
                collection.find(Document(EntityExtraField.SCHEDULE_ENTITY_FIELD_DEPT_ID, deptId))
                    .firstOrNull()
                    ?: throw NoSuchElementException("No schedule available")
            scheduleService.parseAsClassScheduleReadEntityOrThrow(document.toJson())
        }
    }

    suspend fun updateOrThrow(id: String, json: String) = MongoDBClient.updateOneOrThrow(
        databaseName = DATABASE_NAME,
        collectionName = collection,
        data = json,
        query = Filters.eq(ClassScheduleReadEntity::id.name, id)
    )

}
