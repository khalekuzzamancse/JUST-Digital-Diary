@file:Suppress("UnUsed")

package data.schema

import kotlinx.serialization.Serializable

@Serializable
internal enum class HolidayType {
    OnlyClassOff,
    AllOff,
    OnlyOfficeOff,
    SpecialDay
}