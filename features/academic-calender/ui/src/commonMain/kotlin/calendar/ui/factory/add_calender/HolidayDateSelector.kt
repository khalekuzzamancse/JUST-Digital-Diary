package calendar.ui.factory.add_calender

import calendar.ui.model.CalendarGridCell
import kotlinx.coroutines.flow.StateFlow

/**
 * - Manages the selection of dates when an admin edits the calendar to add holidays.
 *  - It allows selecting and deselecting dates within a month and provides the selected dates upon request.
 *  - It also maintains the selection state, clearing it when necessary, such as after holidays are added.
 *
 * @property selectedCalendarCells An observable `StateFlow<Set<CalendarCellUiModel>>` containing the currently selected calendar cells.
 * @property selectedDays A `List<Int>` representing the dates (days of the month as ordinal 1 to last date of month) currently selected.
 * @property toggleDateSelection Toggles the selection state of a given calendar cell (`CalendarCellUiModel`).
 * @property clearSelections Clears all current selections.
 *
 */

interface HolidayDateSelector {
    val selectedCalendarCells: StateFlow<Set<CalendarGridCell>>
    val selectedDays: List<Int>
    fun toggleDateSelection(cell: CalendarGridCell)
    fun clearSelections()
}
