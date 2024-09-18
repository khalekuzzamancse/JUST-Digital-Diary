@file:Suppress("UnUsed")
package academic_calender_domain.model


/**
 *
 * - Represents the `data structure` that will be used to send and receive `data` to and from the `:ui` and `:data` modules
 * - Consumer modules should not directly use this for their own purposes; for example, the `view/UI` should not use it as `viewData`,
 *      and the `:data` module should use it as an `entity` or `schema` to avoid tight coupling with this module
 * - This `model` is for the `domain` module only, where the `domain` module is the implemented version of Clean Architecture's `application` layer
 * - The `:ui` module should convert this `model` to a UI-friendly model via a `Presenter` before using it
 * @param reason reason for the day being holiday
 * @param day If the month has 30 days, the valid range is [1-30] If the month has 31 days, the valid range is [1-31]
 */
data class Holiday(
    val day:Int,
    val holidayType:HolidayType,
    val reason:String
)
