@file:Suppress("functionName")

package data.monggodb.db

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import domain.factory.ContractFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.Document
import org.bson.conversions.Bson
import org.bson.types.ObjectId

/**
 * A singleton object for managing MongoDB client connections and operations.
 */
object MongoDBClient {
    const val COLLECTION_FACULTY = "faculties"
    const val COLLECTION_DEPARTMENT = "departments"
    const val COLLECTION_TEACHER = "teachers"
    const val DATABASE_NAME = "JustDiary"
    private val connectionString = System.getenv("MONGO_URL") ?: "null"

    private val serverApi = ServerApi.builder()
        .version(ServerApiVersion.V1)
        .build()

    private val mongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(ConnectionString(connectionString))
        .serverApi(serverApi)
        .build()

    private val client: MongoClient = MongoClient.create(mongoClientSettings)


    /**
     * Retrieves the MongoDatabase instance for the specified database name.
     *
     * @param databaseName The name of the database to connect to.
     * @return The MongoDatabase instance for the specified database name.
     * @throws Throwable if the database connection fails.
     */
    private fun getDbOrThrow(databaseName: String): MongoDatabase {
        return client.getDatabase(databaseName)
    }

    /**
     * Executes a read operation on the specified database and collection.
     *
     * @param databaseName The name of the database.
     * @param collectionName The name of the collection.
     * @param operation The read operation to be performed on the database.
     * @return The result of the read operation.
     * @throws Throwable if the read operation fails.
     */
    suspend fun <T> readOrThrow(
        databaseName: String,
        collectionName: String,
        operation: suspend (MongoDatabase) -> T
    ): T {
        return withContext(Dispatchers.IO) {
            val database = getDbOrThrow(databaseName)
            operation(database)
        }

    }

    /**
     * Executes a write operation on the specified database and collection.
     *
     * @param databaseName The name of the database.
     * @param collectionName The name of the collection.
     * @param operation The write operation to be performed on the database.
     * @return The result of the write operation.
     * @throws Throwable if the write operation fails.
     */
    suspend fun <T> writeOrThrow(
        databaseName: String,
        operation: suspend (MongoDatabase) -> T
    ): T {
        return withContext(Dispatchers.IO) {
            val database = getDbOrThrow(databaseName)
            operation(database)

        }
    }


    /**
     * Updates a document in the specified collection by _id.
     *
     * @param databaseName The name of the database.
     * @param collectionName The name of the collection.
     * @param id The _id of the document to update.
     * @param updateFields A map of field names to their new values.
     * @return The number of documents updated.
     * @throws Throwable if the update operation fails.
     */
    suspend fun updateDocumentById(
        databaseName: String,
        collectionName: String,
        id: String,
        updateFields: Map<String, Any>
    ): Long {
        return writeOrThrow(databaseName) { database ->
            val collection = database.getCollection<Document>(collectionName)

            // Create a list of update operations
            val updates = updateFields.map { (key, value) ->
                Updates.set(key, value)
            }

            // Combine all the update operations into a single Bson object
            val combinedUpdates = Updates.combine(updates)

            // Perform the update operation
            val result = collection.updateOne(Filters.eq("_id", ObjectId(id)), combinedUpdates)
            result.modifiedCount
        }
    }

    /**
     * @return on success return success message as FeedbackEntity json format, on failure return failure message
     * @throws com.mongodb.MongoWriteException or Throwable
     */
    suspend fun updateOneOrThrow(
        databaseName: String,
        collectionName: String,
        query: Bson,
        jsonUpdate: String
    ): String {
        val feedbackService = ContractFactory.feedbackService()

        return writeOrThrow(databaseName) { database ->
            val collection = database.getCollection<Document>(collectionName)
            val updateDocument = Document.parse(jsonUpdate)
            val updates = Updates.combine(updateDocument.map { (key, value) ->
                Updates.set(key, value)
            })
            val result = collection.updateOne(filter = query, updates)

            if (result.modifiedCount > 0)
                feedbackService.toFeedbackMessage("Updated successfully")
            else
                feedbackService.toFeedbackMessage("Failed to update")
        }
    }


    /**
     * Deletes a document in the specified collection by _id.
     *
     * @param databaseName The name of the database.
     * @param collectionName The name of the collection.
     * @param id The _id of the document to delete.
     * @return The number of documents deleted.
     * @throws Throwable if the delete operation fails.
     */
    suspend fun deleteDocumentById(
        databaseName: String,
        collectionName: String,
        id: String
    ): Long {
        return writeOrThrow(databaseName) { database ->
            val collection = database.getCollection<Document>(collectionName)
            val result = collection.deleteOne(Filters.eq("_id", ObjectId(id)))
            result.deletedCount
        }
    }

}
