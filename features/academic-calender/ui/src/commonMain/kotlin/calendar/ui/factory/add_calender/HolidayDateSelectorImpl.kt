package calendar.ui.factory.add_calender

import calendar.ui.model.CalendarGridCell
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * - Though it has no dependencies, should not create instances of it via the constructor; instead,
 * use a factory method or dependency injection (DI) to obtain an instance
 * - See [HolidayDateSelector] docs
 */
internal class HolidayDateSelectorImpl : HolidayDateSelector {
    private val _selectedCells = MutableStateFlow<Set<CalendarGridCell>>(emptySet())
    override val selectedCalendarCells: StateFlow<Set<CalendarGridCell>> = _selectedCells.asStateFlow()

    override val selectedDays: List<Int>
        get() = _selectedCells.value.mapNotNull { it.dayOfMonth }

    override fun toggleDateSelection(cell: CalendarGridCell) {
        val isAlreadySelected = _selectedCells.value.contains(cell)

        if (isAlreadySelected) {
            _selectedCells.removeFromSelection(cell)
        } else {
            _selectedCells.addToSelection(cell)
        }
    }

    override fun clearSelections() {
        _selectedCells.update { emptySet() }
    }

    // Private extension functions for adding and removing selections
    private fun MutableStateFlow<Set<CalendarGridCell>>.addToSelection(cell: CalendarGridCell) =
        this.update { it + cell }

    private fun MutableStateFlow<Set<CalendarGridCell>>.removeFromSelection(cell: CalendarGridCell) =
        this.update { it - cell }
}