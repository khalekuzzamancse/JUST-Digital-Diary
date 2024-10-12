package core.database.monggodb.services

import com.mongodb.MongoException
import com.mongodb.MongoTimeoutException
import com.mongodb.MongoWriteException
import core.database.monggodb.entity.MessageEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal fun toFeedbackMessage(e: Throwable): String {
    val json = Json { prettyPrint = true }
    return try {
        val msg = when (e) {
            is MongoWriteException -> MessageEntity("Failed to add faculty. Please try again later.")
            is MongoTimeoutException -> MessageEntity("Request timed out. Please check your connection.")
            is MongoException -> MessageEntity("A database error occurred. Please contact support.")
            else -> MessageEntity("An unexpected error occurred at server")
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