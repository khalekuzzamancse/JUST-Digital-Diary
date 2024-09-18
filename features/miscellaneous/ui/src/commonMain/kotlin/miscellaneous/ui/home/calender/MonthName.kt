package miscellaneous.ui.home.calender
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
