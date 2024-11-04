package core.roomdb
import core.customexception.CustomException
import domain.factory.ContractFactory


internal inline fun insertionWithHandleException(block: () -> Unit): String {
    val service = ContractFactory.feedbackService()
    return try {
        "insertionWithHandleException():Not implement yet"
//        val result = block()
//        if (result.insertedId != null) {
//         //   println("InsertionResult:${result.insertedId}")
//            service.toFeedbackMessage("Insert Successfully")
//        } else {
//            service.toFeedbackMessage("Server Error:Failed to insert")
//        }
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
    if (exception is CustomException) {
        return exception // If already a custom exception, return it
    }

    return CustomException.UnknownCustomException(exception)
}

