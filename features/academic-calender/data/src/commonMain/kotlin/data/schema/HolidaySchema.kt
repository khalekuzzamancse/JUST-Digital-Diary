@file:Suppress("UnUsed")
package data.schema

import kotlinx.serialization.Serializable

@Serializable
internal data class HolidaySchema(
    val day:Int,
    val holidayType:HolidayTypeSchema,
    val reason:String
)
