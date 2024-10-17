package calendar.ui.admin

import calendar.presentationlogic.model.CalendarGridCell
import calendar.presentationlogic.model.HolidayType
import calendar.presentationlogic.model.MonthData
import kotlinx.coroutines.flow.StateFlow

/**
 * - It hold the manage and the event for the [HolidayEditorUI]
 * - Provides both viewing and selection functionalities for the editor UI component used when adding holidays.
 *  -  It allows viewing the calendar and selecting dates from the viewer to add holidays.
 *  - Since it has two responsibilities—viewing and selecting—it has two reasons to change.
 *  - To adhere to the Single Responsibility Principle and reduce class complexity, the implementor should delegate
 *   viewing-related logic to a viewing interface and selection-related logic to a selection interface.
 *   - This ensures that changes in view-related logic affect only the viewing interface, and changes in selection-related logic
 *   affect only the selection interface, promoting maintainability and reducing the likelihood of creating a "monster" class.
 *   - So `implement` it such as that works as `Facade` for the `EditorUI` so that it  need not to directly manage the complexity for viewing and selecting
 *
 *
 * @property currentMonthData An observable `StateFlow<MonthDataUiModel?>` representing the current month's calendar data.
 * @property currentYear An observable `StateFlow<Int?>` representing the current year.
 * @property selectedDates An observable `StateFlow<Set<CalendarCellUiModel>>` containing the currently selected calendar cells.
 *
 * @property onSelectionRequest Handles a selection request for a given calendar cell (`CalendarCellUiModel`).
 * @property onHolidayConfirm Confirms the addition of a holiday with the specified `reason: String` and `type: HolidayTypeUiModel`.
 * @property goToNextMonthCalender Navigates to the next month's calendar.
 * @property goToPreviousMonthCalender Navigates to the previous month's calendar.
 *
 */

internal interface HolidayEditorController {
    val currentMonthData: StateFlow<MonthData?>
    val currentYear: StateFlow<Int?>
    val selectedDates: StateFlow<Set<CalendarGridCell>>
    fun getYearData():List<MonthData>

    fun onSelectionRequest(cell: CalendarGridCell)
    fun onHolidayConfirm(reason: String, type: HolidayType)
    fun goToNextMonthCalender()
    fun goToPreviousMonthCalender()

}
