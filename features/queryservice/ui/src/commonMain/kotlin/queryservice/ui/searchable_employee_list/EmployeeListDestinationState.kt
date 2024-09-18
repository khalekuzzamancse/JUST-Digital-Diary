package queryservice.ui.searchable_employee_list


data class EmployeeListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val employee:List<Employee> = emptyList()
)
