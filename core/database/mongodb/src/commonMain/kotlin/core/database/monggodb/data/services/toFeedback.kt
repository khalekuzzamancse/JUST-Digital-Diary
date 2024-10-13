package core.database.monggodb.data.services

import com.mongodb.MongoException
import com.mongodb.MongoTimeoutException
import com.mongodb.MongoWriteException
import core.database.monggodb.domain.model.FeedbackEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal fun toFeedback(e: Throwable): String {
    val json = Json { prettyPrint = true }
    return try {
        val msg = when (e) {
            is MongoWriteException -> FeedbackEntity("Failed to add faculty. Please try again later.")
            is MongoTimeoutException -> FeedbackEntity("Request timed out. Please check your connection.")
            is MongoException -> FeedbackEntity("A database error occurred. Please contact support.")
            else -> FeedbackEntity("An unexpected error occurred at server")
        }
        json.encodeToString(msg)

    } catch (e: Throwable) {
        """
      {
       "message": "Internal Server Error",
      }
        """.trimIndent()
    }

}