package calendar.ui.common.model

/**
 * - Represent data or state of  a single cell of the calender Grid
 * @param dayOfMonth represent the date ordinal such  as 1,2...31, nullable because a month have
 * at most 31 days so the remaining(among 35) GridCell will be empty
 * @param dayOfWeek such as Sat,Sun...Fri
 * @param holiday null when this day is not a holiday
 **/
data class CalendarGridCell(
    val cellIndex: Int,
    val dayOfMonth: Int? = null,
    val dayOfWeek: DayOfWeek? = null,
    val holiday: Holiday? = null,
)

