package administration.navgraph

import administration.ui.offices.officelist.components.OfficeListState
import administration.ui.suboffice.subofficelist.SubOfficeListState
import common.newui.CustomSnackBarData

data class AdminOfficesDestinationState(
    val isLoading: Boolean = false,
    val snackBarData: CustomSnackBarData?=null,
    val officeState: OfficeListState = OfficeListState(),
    val subOfficeState: SubOfficeListState?=null,
    val showSubOfficeList: Boolean = false,
)
