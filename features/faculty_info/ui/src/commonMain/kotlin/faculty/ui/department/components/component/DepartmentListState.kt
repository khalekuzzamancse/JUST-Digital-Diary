package faculty.ui.department.components.component

data class DepartmentListState(
    val title: String? = null,
    val enableBackNavigation: Boolean = true,
    val departments: List<Department> = emptyList(),
    val selected: Int?=null
)