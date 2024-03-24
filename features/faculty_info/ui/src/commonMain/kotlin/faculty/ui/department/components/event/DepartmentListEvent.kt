package faculty.ui.department.components.event

interface DepartmentListEvent {
    data class DepartmentSelected(val index: Int) : DepartmentListEvent
}
