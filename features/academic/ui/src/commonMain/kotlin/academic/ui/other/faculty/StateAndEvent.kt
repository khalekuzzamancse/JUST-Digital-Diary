package academic.ui.other.faculty

import academic.ui.model.FacultyModel

data class FacultyListState(
    val faculties: List<FacultyModel> = emptyList(),
    val selected:Int?=null
)

interface FacultyListEvent {
    data class FacultySelected(val index: Int) : FacultyListEvent
}
