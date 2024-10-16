@file:Suppress("unused")

package data.monggodb.db

import com.mongodb.client.model.Filters
import data.monggodb.db.MongoDBClient.COLLECTION_CALENDAR
import data.monggodb.db.MongoDBClient.DATABASE_NAME
import data.monggodb.db.MongoDBClient.ID_FIELD
import domain.entity.calender.AcademicCalenderEntity
import domain.factory.ContractFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.Document
import java.time.LocalDate

internal class CalenderCollection {
    private val insertionService = ContractFactory.calenderInsertionService()
    private val readService = ContractFactory.calenderReadEntityService()
    private val service = ContractFactory.feedbackService()


    suspend fun insert(json: String): String {
        val result = insertionService.processWriteEntityOrThrow(json = json)
        val doc = Document.parse(result.json)
            .append(ID_FIELD, result.primaryKey)
        return MongoDBClient.insertOne(collectionName = COLLECTION_CALENDAR, doc)
    }

    /**Useful for admin to read all and delete the old once*/
    @Throws(Throwable::class)
    suspend fun readAll(): String {
        return MongoDBClient.writeOrThrow(
            DATABASE_NAME
        ) { database ->
            val collection = database.getCollection<Document>(COLLECTION_CALENDAR)

            val jsonArray = collection.find().toList().map { document ->
                readService.parseOrThrow(document.toJson())
            }
            // Join all the JSON strings into a single JSON array
            "[${jsonArray.joinToString(",")}]"
        }
    }

    @Throws(Throwable::class)
    suspend fun readOfCurrentYear(): String {
        return MongoDBClient.readOrThrow(
            DATABASE_NAME
        ) { database ->
            val collection = database.getCollection<Document>(COLLECTION_CALENDAR)
            val year: Int = LocalDate.now().year
            val document =
                collection.find(Document(AcademicCalenderEntity::year.name, year)).firstOrNull()
                    ?: throw NoSuchElementException("No calendar available for $year")

            readService.parseOrThrow(document.toJson())
        }
    }

    suspend fun updateOrThrow(year: Int, json: String) = MongoDBClient.updateOneOrThrow(
        databaseName = DATABASE_NAME,
        collectionName = COLLECTION_CALENDAR,
        data = json,
        query = Filters.eq(AcademicCalenderEntity::year.name, year)
    )

}
