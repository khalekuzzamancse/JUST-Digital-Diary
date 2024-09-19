@file:Suppress("UnUsed")

package domain.model

import domain.docs.ModelDoc

/**
 * Further discussion on:
 *  - `Model`: see [ModelDoc]
 */
enum class HolidayType {
    OnlyClassOff,
    AllOff,
    OnlyOfficeOff,
    SpecialDay
}