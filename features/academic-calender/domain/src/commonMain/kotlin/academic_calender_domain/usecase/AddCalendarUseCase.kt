@file:Suppress("UnUsed")
package academic_calender_domain.usecase

import academic_calender_domain.exception.CalendarFeatureException
import academic_calender_domain.model.AcademicCalendar
import academic_calender_domain.repository.CalenderRepository
import academic_calender_domain.service.CalendarService

/**
 * - Use case for adding a calendar to the `repository`
 *
 * What is a use case:
 * - Use cases expect pre-validated input and do not handle validation themselves, but they may perform authorization checks
 * - It ensures separation of concerns by encapsulating one specific operation
 *
 * Why use a use case:
 * - Use cases ensure that only the necessary operation is exposed to the `Controller`, preventing access to other methods in the repository
 * - They encapsulate business logic and prevent the `UI` or `Controller` from interacting directly with the repository, promoting a cleaner, more maintainable codebase
 * - By using a use case, you adhere to the `command pattern`, which provides additional flexibility such as the ability to implement undo/redo functionality
 *
 * What problem is it solving:
 * - Without use cases, a `controller` might directly interact with the `repository` and have access to multiple operations (e.g., add, update, delete),
 *   leading to unintended behavior or tightly coupled code
 * - A use case solves this by encapsulating the required operation, ensuring that the controller can only perform the intended task
 * 
 * Why constructor dependency injection:
 * - allowing for easy switching between automatic and manual `dependency injection`, and vice versa
 *
 * Why it returns an exception instead of throwing:
 * - If we were to throw an exception, it could be any type of [Exception], which might be difficult to handle uniformly
 *
 * Who should use it:
 * - The `Controller` in the `UI` layer should invoke this use case to add a calendar after data has been pre-validated and authorized
 * - This use case is specifically designed to handle the add operation, making sure the controller doesn't access `Repository`
 *
 *  Why the consumer module should not directly create instance of  use cases:
 *
 * - Creating a use case requires access to the repository.Exposing the repository directly to the consumer layer would violate the separation of
 * concerns and could lead to unwanted direct interactions with the repository.
 *  - Instead, the `di:` module (Dependency Injection layer) should provide instances of use cases via a factory or DI container.
 *  - Using a `factory method pattern` for creating use cases maintains a single source of truth for instance creation, ensuring consistent and centralized object management
 *  - This approach also allows better control over dependencies, making testing, maintenance, and swapping implementations easier without exposing the repository
 *
 */
class AddCalendarUseCase(
    private val repository: CalenderRepository,
    private val validationService: CalendarService
) {
    /**
     * Executes the use case for adding a calendar
     *
     * @param calendar The [AcademicCalendar] to be added
     * @return [CalendarFeatureException]? Returns an exception if validation or adding fails, otherwise `null` on success
     */
    fun execute(calendar: AcademicCalendar): CalendarFeatureException? {
        val validationError = validationService.validateCalender(calendar)
        if (validationError != null)
            return validationError
        return repository.addCalender(calendar)
    }
}
