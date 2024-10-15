@file:Suppress("unused")

package domain.factory

import domain.service.FeedbackMessageService
import domain.service.JsonParser
import domain.service.InsertionService
import domain.service.ReadEntityParserService

object ContractFactory {
    fun jsonParser(): JsonParser = JsonParserImpl()
    fun feedbackService(): FeedbackMessageService = FeedbackMessageServiceImpl()
    fun insertionService(): InsertionService = InsertionServiceImpl()
    fun readEntityParserService(): ReadEntityParserService = ReadEntityParserServiceImpl()
}