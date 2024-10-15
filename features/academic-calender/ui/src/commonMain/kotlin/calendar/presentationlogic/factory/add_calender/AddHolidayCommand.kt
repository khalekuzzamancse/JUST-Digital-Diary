package calendar.presentationlogic.factory.add_calender

import calendar.presentationlogic.model.HolidayType
import calendar.presentationlogic.model.MonthData
/**
 * - Encapsulates a single operation for adding holidays to selected dates.
 *   Instead of implementing this operation as a function in the `EditorController`, we encapsulate it into a command.
 *   This reduces the responsibility and code length of `EditorController`.
 *   Using the concept of the Command Pattern, this allows for easy implementation of undo functionality.
 *   - Undo is available.
 */
internal interface AddHolidayCommand {
    /**
     * Executes the command to add holidays to the selected dates.
     *
     * @param yearData A mutable list (`List<MonthDataUiModel>`) representing data for all months of the year.
     * @param dateOrdinals A list of integers (`List<Int>`) representing the selected dates (days of the month) to which holidays will be added.
     * @param reason A `String` representing the reason for the holiday.
     * @param type A `HolidayTypeUiModel` representing the type of the holiday, which includes properties like color code.
     * @param monthOrdinal An `Int` representing the index of the month (0-11) in which the holidays are to be added.
     * @return An updated list (`List<MonthData>`) containing the modified month data with the holidays added.
     */
    fun execute(
        yearData: List<MonthData>,
        dateOrdinals: List<Int>,
        reason: String,
        type: HolidayType,
        monthOrdinal: Int
    ): List<MonthData>

    fun undo(): List<MonthData>
}
