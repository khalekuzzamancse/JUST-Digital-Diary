package core.database.monggodb.data.database

import com.mongodb.client.result.InsertOneResult
import core.database.monggodb.domain.model.FeedbackEntity
import core.database.monggodb.data.services.toFeedback
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * - Return the [FeedbackEntity] as Json format(String),for success or failure
 */
internal inline fun withExceptionHandle(block: () -> InsertOneResult): String {
    val json = Json { prettyPrint = true }
    return try {
        val result = block()
        if (result.insertedId != null) {
            json.encodeToString(FeedbackEntity("added successfully!"))
        } else {
            json.encodeToString(FeedbackEntity("Server Error: Failed to add"))
        }
    } catch (e: Exception) {
        toFeedback(e)
    }
}