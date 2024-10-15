package calendar.presentationlogic.model

/**
 * - Represent the holiday
 * @param colorCode based on type of holiday such as AllClose,UniversityDay,Weekend ..etc pass different color
 */
data class Holiday(
    val reason: String,
    val type: HolidayType,
)
