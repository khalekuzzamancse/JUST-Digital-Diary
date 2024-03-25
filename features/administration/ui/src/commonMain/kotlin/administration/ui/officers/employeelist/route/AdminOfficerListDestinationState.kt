package administration.ui.officers.employeelist.route

import administration.ui.officers.employeelist.components.AdminOfficerListState

data class AdminOfficerListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val adminOfficerListState: AdminOfficerListState = AdminOfficerListState(),
)
