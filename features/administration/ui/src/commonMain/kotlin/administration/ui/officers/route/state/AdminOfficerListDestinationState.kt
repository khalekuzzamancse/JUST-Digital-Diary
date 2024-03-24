package administration.ui.officers.route.state

import administration.ui.officers.components.state.AdminOfficerListState

data class AdminOfficerListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val adminOfficerListState: AdminOfficerListState =AdminOfficerListState(),
)
