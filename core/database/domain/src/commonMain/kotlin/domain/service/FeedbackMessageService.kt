@file:Suppress("unused")
package domain.service

interface FeedbackMessageService {
    fun toFeedbackMessage(message: String): String
    fun toFeedbackMessage(exception: Throwable): String
    fun isFeedbackEntity(json: String):Boolean
}