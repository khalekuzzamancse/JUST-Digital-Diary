@file:Suppress("UnUsed")
package data.schema

import kotlinx.serialization.Serializable

@Serializable
internal data class Holiday(
    val day:Int,
    val holidayType:HolidayType,
    val reason:String
)
