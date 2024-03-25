package administration.ui.offices.officelist.route

import administration.ui.offices.officelist.components.OfficeListState
import administration.ui.suboffice.subofficelist.SubOfficeListState

data class AdminOfficesDestinationState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val officeState: OfficeListState = OfficeListState(),
    val subOfficeState: SubOfficeListState?=null,
    val showSubOfficeList: Boolean = false,
)
