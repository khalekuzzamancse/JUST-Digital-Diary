@file:Suppress("UnUsed")
package calender.common


/**
 * - Represent data or state of  a single cell of the calender Grid
 * @param dayOrdinal represent the date ordinal such  as 1,2...31, nullable because a month have at most 31 days so the remaining GridCell will be empty
 * @param dayName such as Sat,Sun...Fri
 * @param holiday null when this day is not a holiday
 **/
data class CalendarCellUiModel(
    val cellNo: Int,
    val dayOrdinal: Int? = null,
    val dayName: DayName? = null,
    val holiday: HolidayUiModel? = null,
)

/**
 * - Using for type safety so that later can be used such as group by name, etc
 */
enum class DayName {
    Sat, Sun, Mon, Tue, Wed, Thu, Fri
}

/**
 * - Represent the holiday
 * @param colorHexCode based on type of holiday such as AllClose,UniversityDay,Weekend ..etc pass different color
 */
data class HolidayUiModel(
    val colorHexCode: String,
    val reason: String
)
