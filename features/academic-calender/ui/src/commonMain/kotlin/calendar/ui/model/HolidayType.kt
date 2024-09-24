package calendar.ui.model

/**
 * @param color color hex code
 */
enum class HolidayType(val color:String) {
    AllOff("#FF0000"),    // Red color hex code for AllOff
    OnlyClassOf("#00FF00"), // Green color hex code for OnlyClassOff
    SpecialDay("#800080")//Purple color hex code for SpecialDay
}