package data.service

import schedule.data.service.JsonHandler

/**
 * - After Implementing the Repositories, found this portion is common for all methods, that is why
 * separating it, by using the concept of Slot or Strategy Design pattern
 */
internal inline fun <T> JsonHandler.withExceptionHandle(apiCall: () -> Result<T>): Result<T> {
    return try {
        apiCall()
    } catch (exception: Throwable) {

        Result.failure(createCustomException(exception))//jsonHandler.createCustomException(exception)
    }
}