package core.database.monggodb.database

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.Document
import org.bson.types.ObjectId

/**
 * A singleton object for managing MongoDB client connections and operations.
 */
object MongoDBClient {
    private val password = System.getenv("MONGODB_PASSWORD") ?: "default_password"
    private val connectionString = "mongodb+srv://khalekuzzamancse:$password@dsavisualizercluster.t0zogph.mongodb.net/?retryWrites=true&w=majority&appName=DSAVisualizerCluster"

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
    private fun getDatabase(databaseName: String): MongoDatabase {
        return try {
            client.getDatabase(databaseName)
        } catch (e: Exception) {
            throw Throwable("Failed to get the database: $databaseName. Error: ${e.message}", e)
        }
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
    suspend fun <T> executeReadOperation(databaseName: String, collectionName: String, operation: suspend (MongoDatabase) -> T): T {
        return withContext(Dispatchers.IO) {
            val database = getDatabase(databaseName)
            try {
                operation(database)
            } catch (e: Exception) {
                throw Throwable("Read operation failed on collection: $collectionName in database: $databaseName. Error: ${e.message}", e)
            }
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
    suspend fun <T> executeWriteOperation(databaseName: String, collectionName: String, operation: suspend (MongoDatabase) -> T): T {
        return withContext(Dispatchers.IO) {
            val database = getDatabase(databaseName)
            try {
                operation(database)
            } catch (e: Exception) {
                throw Throwable("Write operation failed on collection: $collectionName in database: $databaseName. Error: ${e.message}", e)
            }
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
        return executeWriteOperation(databaseName, collectionName) { database ->
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
        return executeWriteOperation(databaseName, collectionName) { database ->
            val collection = database.getCollection<Document>(collectionName)
            val result = collection.deleteOne(Filters.eq("_id", ObjectId(id)))
            result.deletedCount
        }
    }
}
