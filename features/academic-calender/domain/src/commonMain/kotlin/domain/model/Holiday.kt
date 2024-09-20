@file:Suppress("UnUsed")
package domain.model


import common.docs.domain_layer.ModelDoc


/**
 *
 Further discussion on:
 *  - `Model`: see [ModelDoc]
 * @param reason reason for the day being holiday
 * @param day If the month has 30 days, the valid range is [1-30] If the month has 31 days, the valid range is [1-31]
 */
data class Holiday(
    val day:Int,
    val holidayType:HolidayType,
    val reason:String
)

/**
 * Further discussion on:
 *  - `Model`: see [ModelDoc]
 */
enum class HolidayType {
    OnlyClassOff,
    AllOff,
    OnlyOfficeOff,
    SpecialDay,
    Weekend

}