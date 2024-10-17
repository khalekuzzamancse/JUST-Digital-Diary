
package data.entity

import kotlinx.serialization.Serializable

/**
 * @property holidays must be 12 array size where index 0 represents `January`, so on so forth.
 * Each of inner list represent holidays for month
 *
 *
 */
@Serializable
internal data class AcademicCalenderEntity(
    val year:Int,
    val holidays:List<List<HolidayEntity>>
)

