package data.monggodb.factory

import com.mongodb.client.result.InsertOneResult
import domain.exception.CustomException
import domain.factory.ContractFactory

internal inline fun insertionWithHandleException(block: () -> InsertOneResult): String {
    val service= ContractFactory.feedbackService()
    return try {
        val result = block()
        if (result.insertedId != null) {
            service.toFeedbackMessage("Insert successfully")
        } else {
            service.toFeedbackMessage("Server Error:Failed to insert")
        }
    } catch (e: Exception) {
        service.toFeedbackMessage(toCustomException(e))
    }
}
suspend fun withExceptionHandle( operation: suspend () -> String): String {
    val feedbackService = ContractFactory.feedbackService()
    return try {
        operation()
    } catch (e: Exception) {
        feedbackService.toFeedbackMessage(toCustomException(e))
    }
}
fun toCustomException(throwable: Throwable): CustomException {
    return when (throwable) {
        is com.mongodb.MongoSocketOpenException -> {
            CustomException.ConnectionFailedException(throwable)
        }
        is com.mongodb.MongoWriteException -> {
            if (throwable.error.category == com.mongodb.ErrorCategory.DUPLICATE_KEY) {
                CustomException.DataAlreadyExistsException("MongoDB Document")
            } else {
                CustomException.DataConflictException("MongoDB Document")
            }
        }
        is com.mongodb.MongoQueryException -> {
            CustomException.InvalidQueryException(throwable.message ?: "Unknown query issue")
        }
        is com.mongodb.MongoCommandException -> {
            CustomException.JsonParseException(throwable.errorMessage ?: "Unknown MongoDB command error")
        }
        is com.mongodb.MongoSecurityException -> {
            CustomException.UnauthorizedAccessException()
        }
        else -> {
            CustomException.UnknownCustomException(throwable)
        }
    }
}
