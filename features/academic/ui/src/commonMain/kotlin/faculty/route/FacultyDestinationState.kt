package faculty.route

data class FacultiesScreenState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val openDepartmentListDestination: Boolean=false
)
