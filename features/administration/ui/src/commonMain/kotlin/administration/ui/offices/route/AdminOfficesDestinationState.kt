package administration.ui.offices.route

import administration.ui.offices.components.components.office_list.state.OfficeListState
import administration.ui.sub_offices.component.component.sub_office_list.state.SubOfficeListState

data class AdminOfficesDestinationState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val officeState: OfficeListState = OfficeListState(),
    val subOfficeState: SubOfficeListState?=null,
    val showSubOfficeList: Boolean = false,
)
