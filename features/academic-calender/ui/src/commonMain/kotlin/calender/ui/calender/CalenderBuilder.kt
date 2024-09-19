package calender.ui.calender

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

/**
 * * Its okay to use a singleton because the calender are globally same
 */
object CalenderBuilder {
    /**
     * * This a large function refactor is small small chunks
     */
    fun currentYearCalender(): CurrentYearCalender {
        val currentYear = YearMonth.now().year
        val allMonthsData = mutableListOf<List<DateOfMonth>>()
        for (month in 1..12) {
            val firstDayOfMonth = LocalDate.of(currentYear, month, 1)
            val lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1)

            val dateOfMonthList = mutableListOf<DateOfMonth>()

            var currentDate = firstDayOfMonth
            while (currentDate.isBefore(lastDayOfMonth) || currentDate.isEqual(lastDayOfMonth)) {
                val formattedDate = currentDate.dayOfMonth
                val dayName = currentDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                val dateOfMonth = DateOfMonth(
                    formattedDate,
                    dayName.toDayName()
                )
                dateOfMonthList.add(dateOfMonth)
                currentDate = currentDate.plusDays(1)
            }

            allMonthsData.add(dateOfMonthList)
        }

        // Access the list for further use
        val months = mutableListOf<MonthOfYear>()
        for ((index, monthData) in allMonthsData.withIndex()) {
            val monthName =
                java.time.Month.of(index + 1).getDisplayName(TextStyle.FULL, Locale.ENGLISH)
            months.add(MonthOfYear(monthName, monthData))
        }
        return CurrentYearCalender(
            name = currentYear.toString(),
            months = months
        )

    }

    private fun String.toDayName(): DayName {
        return when (this.lowercase(Locale.getDefault())) {
            "saturday" -> DayName.Saturday
            "sunday" -> DayName.Sunday
            "monday" -> DayName.Monday
            "tuesday" -> DayName.Tuesday
            "wednesday" -> DayName.Wednesday
            "thursday" -> DayName.Thursday
            "friday" -> DayName.Friday
            else -> throw IllegalArgumentException("Invalid dayName: $this")
        }
    }

}
data class CurrentYearCalender(
    val name: String,
    val months: List<MonthOfYear>
)
data class DateOfMonth(
    val date: Int,
    val dayName: DayName,
)

/**
 * * For type safety it is used,instead of hardcoded string,string can causes more bug
 * * because string are able to maintain single source of truth
 */
enum class DayName (order:Int){
    Saturday(0),
    Sunday(1),
    Monday(2),
    Tuesday(3),
    Wednesday(4),
    Thursday(5),
    Friday(6);
}

/**
 * * For type safety it is used,instead of hardcoded string,string can causes more bug
 * * because string are able to maintain single source of truth
 */
enum class MonthName(val order: Int) {
    January(0),
    February(1),
    March(2),
    April(3),
    May(4),
    June(5),
    July(6),
    August(7),
    September(8),
    October(9),
    November(10),
    December(11)
}

data class MonthOfYear(
    val name: String,
    val dates: List<DateOfMonth>
)