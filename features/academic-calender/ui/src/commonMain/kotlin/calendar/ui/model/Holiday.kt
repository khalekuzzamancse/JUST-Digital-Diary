package calendar.ui.model

/**
 * - Represent the holiday
 * @param colorCode based on type of holiday such as AllClose,UniversityDay,Weekend ..etc pass different color
 */
data class Holiday(
    val colorCode: String,
    val reason: String
)
