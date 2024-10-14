@file:Suppress("unused")
package domain.factory

import domain.entity.FeedbackMessageEntity
import domain.exception.CustomException
import domain.service.FeedbackMessageService
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

 class FeedbackMessageServiceImpl internal  constructor(): FeedbackMessageService {
    private val json = Json { prettyPrint = true }

    override fun toFeedbackMessage(message: String): String {
        return json.encodeToString(FeedbackMessageEntity(message))
    }

    override fun toFeedbackMessage(exception: Throwable): String {
        return if (exception is CustomException)
            json.encodeToString(FeedbackMessageEntity(exception.message))
        else
            json.encodeToString(FeedbackMessageEntity(exception.javaClass.simpleName))
    }
}
