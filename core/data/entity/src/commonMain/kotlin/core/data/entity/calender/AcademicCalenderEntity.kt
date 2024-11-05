@file:Suppress("unused")
package core.data.entity.calender

import kotlinx.serialization.Serializable

/**
 * Used for both `READ` and `WRITE`
 * @property holidays must be 12 array size where index 0 represents `January`, so on so forth.
 * Each of inner list represent holidays for month
 *
 *
 */
@Serializable
data class AcademicCalenderEntity(
    val year:Int,
    val holidays:List<List<HolidayEntity>>
)

/**
 * @property day each value between `1 to 31`,since a month can have `max date=31`
 * @property type right now there is `3 types, 1=AllOff, 2=OnlyClassOff, 3=SpecialDay`
 */
@Serializable
data class HolidayEntity(
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
