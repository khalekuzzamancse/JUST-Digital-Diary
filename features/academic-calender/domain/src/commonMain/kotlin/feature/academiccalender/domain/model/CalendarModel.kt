@file:Suppress("unused")

package feature.academiccalender.domain.model

import common.docs.ModelDoc
import java.time.Month

data class CalendarModel(
    val year: Int,
    val months: List<MonthModel>
)

data class MonthModel(
    val month: Month,//java
    val days: List<DayModel>
)

data class DayModel(
    val name: DayOfWeek,
    val date: Int,//such as 1,2,3...31 ; it's represent the dates
    val holiday: HolidayModel? = null
) {
    val isHoliday = holiday != null
}

data class HolidayModel(
    val type: HolidayType,
    val reason: String
)

/**
 *
Further discussion on:
 *  - `Model`: see [ModelDoc]
 * @param reason reason for the day being holiday
 * @param day If the month has 30 days, the valid range is [1-30] If the month has 31 days, the valid range is [1-31]
 */
data class Holiday(
    val day: Int,
    val holidayType: HolidayType,
    val reason: String
)

/**
 * Further discussion on:
 *  - `Model`: see [ModelDoc]
 *
 */
enum class HolidayType {
    /** Office open but class closed*/
    OnlyClassOff,
    /** both Office and class off such for weekend or other reason*/
    AllOff,
    /** Such as University day or other reason*/
    SpecialDay,
}

enum class DayOfWeek {
    SATURDAY, SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
}

