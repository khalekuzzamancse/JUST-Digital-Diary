@file:Suppress("UnUsed")

package academic_calender_domain.exception


/**
 *  - Represents the `data structure` that will be used to send and receive `data` to and from the `:ui` and `:data` modules
 *  - Consumer modules should not directly use this for their own purposes; for example, the `view/UI` should not use it as `viewData`,
 *   and the `:data` module should use it as an `entity` or `schema` to avoid tight coupling with this module
 *   - This `model` is for the `domain` module only, where the `domain` module is the implemented version of Clean Architecture's `application` layer
 *   - The `:ui` module should convert this `model` to a UI-friendly model via a `Presenter` before using it

 * @param debugMessage the message for developer to find the causes or source of error
 * @param message  This represents the message that can be converted to a UI-friendly, short format
 **/

sealed class CalendarFeatureException(message: String, debugMessage: String) :
    Throwable(message = message, cause = Throwable(debugMessage))


/**
 * Exception thrown when an invalid year is provided.
 *
 * @param year The year that is considered invalid.
 */
class InvalidYearFeatureException(year: Int) : CalendarFeatureException(
    message = "The year $year is invalid.",
    debugMessage = "Attempted to use an invalid year: $year."
)

/**
 * Exception thrown when an invalid day is provided for a specific month.
 *
 * @param day The invalid day that was provided.
 */
class InvalidDayFeatureException(day: Int, month:String) : CalendarFeatureException(
    message = "The day $day is invalid for month $month.",
    debugMessage = "Attempted to use an invalid day: $day."
)

/**
 * Exception thrown when a user tries to update the calendar without the necessary permissions.
 *
 * @param year The year for which the update was attempted without permission.
 */
class CalendarUpdatePermissionFeatureException(year: Int) : CalendarFeatureException(
    message = "You do not have permission to update the calendar for the year $year.",
    debugMessage = "Insufficient permissions for modifying the calendar of the year $year."
)

/**
 * Exception thrown for any unknown or unexpected calendar-related errors.
 *
 * @param debugMessage A detailed message describing the unexpected error.
 */
class CalendarUnknownFeatureException(debugMessage: String) : CalendarFeatureException(
    message = "An unknown error occurred with the calendar.",
    debugMessage = debugMessage
)
