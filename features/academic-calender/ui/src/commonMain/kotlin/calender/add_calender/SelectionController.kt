package calender.add_calender

import calender.common.CalendarCellUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
// Interface defining the contract for SelectionController
interface SelectionController {
    val selectedCells: StateFlow<Set<CalendarCellUiModel>>
    val selectedDates: List<Int>
    fun toggleSelection(cell: CalendarCellUiModel)
    fun clearSelection()
}
// Implementation of the SelectionController interface
class SelectionControllerImpl : SelectionController {
    private val _selectedCells = MutableStateFlow<Set<CalendarCellUiModel>>(emptySet())
    override val selectedCells: StateFlow<Set<CalendarCellUiModel>> = _selectedCells.asStateFlow()

    override val selectedDates: List<Int>
        get() = _selectedCells.value.mapNotNull { it.dayOrdinal }

    override fun toggleSelection(cell: CalendarCellUiModel) {
        val isAlreadySelected = _selectedCells.value.contains(cell)

        if (isAlreadySelected) {
            _selectedCells.removeFromSelection(cell)
        } else {
            _selectedCells.addToSelection(cell)
        }
    }

    override fun clearSelection() {
        _selectedCells.update { emptySet() }
    }

    // Private extension functions for adding and removing selections
    private fun MutableStateFlow<Set<CalendarCellUiModel>>.addToSelection(cell: CalendarCellUiModel) =
        this.update { it + cell }

    private fun MutableStateFlow<Set<CalendarCellUiModel>>.removeFromSelection(cell: CalendarCellUiModel) =
        this.update { it - cell }
}