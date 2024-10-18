package core.database.datasource.monggodb.core

import com.mongodb.client.result.InsertOneResult
import domain.exception.CustomException
import domain.factory.ContractFactory

internal inline fun insertionWithHandleException(block: () -> InsertOneResult): String {
    val service = ContractFactory.feedbackService()
    return try {
        val result = block()
        if (result.insertedId != null) {
            println("InsertionResult:${result.insertedId}")
            service.toFeedbackMessage("Insert Successfully")
        } else {
            service.toFeedbackMessage("Server Error:Failed to insert")
        }
    } catch (e: Exception) {
        service.toFeedbackMessage(toCustomException(e))
    }
}

suspend fun withExceptionHandle(operation: suspend () -> String): String {
    return try {
        operation()
    } catch (e: Throwable) {
        val feedbackService = ContractFactory.feedbackService()
        feedbackService.toFeedbackMessage(toCustomException(e))
    }
}

fun toCustomException(exception: Throwable): CustomException {
    return when (exception) {
        is CustomException -> exception//If already custom exception returned it
        is com.mongodb.MongoSocketOpenException -> CustomException.ConnectionFailedException(exception)


        is com.mongodb.MongoWriteException ->
            if (exception.error.category == com.mongodb.ErrorCategory.DUPLICATE_KEY)
                CustomException.DataAlreadyExistsException("MongoDB Document")
             else
                CustomException.DataConflictException("MongoDB Document")



        is com.mongodb.MongoQueryException -> CustomException.InvalidQueryException(
            exception.message ?: "Unknown query issue"
        )


        is com.mongodb.MongoCommandException -> CustomException.JsonParseException(
            exception.errorMessage ?: "Unknown MongoDB command error"
        )


        is com.mongodb.MongoSecurityException -> CustomException.UnauthorizedAccessException()


        else -> CustomException.UnknownCustomException(exception)

    }
}
