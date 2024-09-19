@file:Suppress("UnUsed")
package domain.model

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
    val name: DayNameModel,
    val date: Int,//such as 1,2,3...31 ; it's represent the dates
    val holiday: HolidayModel? = null
) {
    val isHoliday = holiday != null
}

data class HolidayModel(
    val typeColorCode: String,
    val reason: String
)

enum class DayNameModel {
    SATURDAY, SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
}

