package academic.ui.other.department

data class DepartmentListState(
    val title: String? = null,
    val enableBackNavigation: Boolean = true,
    val departments: List<Department> = emptyList(),
    val selected: Int?=null
)
class Department(
    val name: String,
    val shortName: String,
    val id: String,
)
interface DepartmentListEvent {
    data class DepartmentSelected(val index: Int) : DepartmentListEvent
}
