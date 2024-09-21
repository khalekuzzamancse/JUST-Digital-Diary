package calendar.ui.factory

import calendar.ui.editor.HolidayDateSelector
import calendar.ui.common.model.CalendarGridCell
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// Implementation of the SelectionController interface
class SelectionControllerImpl : HolidayDateSelector {
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