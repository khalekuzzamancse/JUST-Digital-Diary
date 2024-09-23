package administration.ui.officers

data class AdminOfficerListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val officers: List<AdminOfficerModel> = emptyList()
)
