@file:Suppress("UnUsed")

package domain.exception
import common.docs.domain_layer.CustomExceptionDoc


/**
 * Further discussion on:
 *   - `Repository`: see [CustomExceptionDoc]
 * @param debugMessage the message for developer to find the causes or source of error
 * @param message  This represents the message that can be converted to a UI-friendly, short format
 **/

sealed class CalendarFeatureException(override val message: String, val debugMessage: String) :
    Throwable(message = message, cause = Throwable(debugMessage)) {
    override fun toString(): String {
        return "CalendarFeatureException: $message\nDebug Message: $debugMessage"
    }


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
     * @param month The name of the month where the invalid day was attempted.
     */
    class InvalidDayFeatureException(day: Int, month: String) : CalendarFeatureException(
        message = "The day $day is invalid for month $month.",
        debugMessage = "Attempted to use an invalid day: $day in month: $month."
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
    class MiscException(message: String,debugMessage: String) : CalendarFeatureException(
        message = message,
        debugMessage = debugMessage
    )

}
