package data.entity

import kotlinx.serialization.Serializable

/**
 * @property day each value between `1 to 31`,since a month can have `max date=31`
 * @property type right now there is `3 types, 1=AllOff, 2=OnlyClassOff, 3=SpecialDay`
 */
@Serializable
internal data class HolidayEntity(
    val day:Int,
    val type:Int,
    val reason:String
){
    companion object {
        const val ALL_OFF = 1
        const val ONLY_CLASS_OFF = 2
        const val SPECIAL_DAY = 3
    }
}
