package academic.ui.other.department

import academic.ui.model.DepartmentModel

data class DepartmentListState(
    val title: String? = null,
    val enableBackNavigation: Boolean = true,
    val departments: List<DepartmentModel> = emptyList(),
    val selected: Int?=null
)

interface DepartmentListEvent {
    data class DepartmentSelected(val index: Int) : DepartmentListEvent
}
