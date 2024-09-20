@file:Suppress("UnUsed")

package data.schema

import kotlinx.serialization.Serializable

@Serializable
internal enum class HolidayTypeSchema {
    /** Office open but class closed*/
    OnlyClassOff,
    /** both Office and class off such for weekend or other reason*/
    AllOff,
    /** Such as University day or other reason*/
    SpecialDay,
}