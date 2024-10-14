@file:Suppress("unused")

package domain.factory

import domain.service.FeedbackMessageService
import domain.service.JsonParser
import domain.service.PrimaryKeyService
import domain.service.ReadEntityParserService

object ContractFactory {
    fun jsonParser(): JsonParser = JsonParserImpl()
    fun feedbackService(): FeedbackMessageService = FeedbackMessageServiceImpl()
    fun primaryKeyService(): PrimaryKeyService = PrimaryKeyServiceImpl()
    fun readEntityParserService(): ReadEntityParserService = ReadEntityParserServiceImpl()
}