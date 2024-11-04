@file:Suppress("unused")
package domain.factory

import core.data.entity.FeedbackMessageEntity
import domain.exception.CustomException
import domain.service.FeedbackMessageService
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

 class FeedbackMessageServiceImpl internal  constructor(): FeedbackMessageService {
    private val parser = Json { prettyPrint = true }

    override fun toFeedbackMessage(message: String): String {
        return parser.encodeToString(FeedbackMessageEntity(message))
    }

    override fun toFeedbackMessage(exception: Throwable): String {
        return if (exception is CustomException)
            parser.encodeToString(FeedbackMessageEntity(exception.message))
        else
            parser.encodeToString(FeedbackMessageEntity(exception.javaClass.simpleName))
    }

     override fun isFeedbackEntity(json: String): Boolean {
         return  try {
               val entity:FeedbackMessageEntity=parser.decodeFromString(json)
               true
           }
           catch (_:Exception){
               false
           }

     }

     override fun asFeedbackEntityOrThrow(json: String):FeedbackMessageEntity{
         try {
           return  parser.decodeFromString(json) as FeedbackMessageEntity
         }
         catch (e:Exception){
             throw  CustomException.JsonParseException(json = json)
         }
     }
 }
