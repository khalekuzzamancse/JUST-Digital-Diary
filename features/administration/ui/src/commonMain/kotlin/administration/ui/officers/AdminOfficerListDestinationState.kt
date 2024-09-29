package administration.ui.officers

import administration.controller_presenter.model.AdminOfficerModel

data class AdminOfficerListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val officers: List<AdminOfficerModel> = emptyList()
)
