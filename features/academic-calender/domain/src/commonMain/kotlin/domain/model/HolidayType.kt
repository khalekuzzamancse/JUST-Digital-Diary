@file:Suppress("UnUsed")

package domain.model


import common.docs.domain_layer.ModelDoc


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