@file:Suppress("UnUsed")

package data.schema

import kotlinx.serialization.Serializable

@Serializable
internal enum class HolidayTypeSchema {
    OnlyClassOff,
    AllOff,
    OnlyOfficeOff,
    SpecialDay,
    Weekend
}