@file:Suppress("unused")
package domain.service

import core.data.entity.FeedbackMessageEntity

interface FeedbackMessageService {
    fun toFeedbackMessage(message: String): String
    fun toFeedbackMessage(exception: Throwable): String
    fun isFeedbackEntity(json: String):Boolean
    fun asFeedbackEntityOrThrow(json: String):FeedbackMessageEntity
}