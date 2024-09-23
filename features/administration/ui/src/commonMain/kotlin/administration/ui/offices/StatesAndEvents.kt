package administration.ui.offices

import administration.ui.model.OfficesModel

data class OfficeListState(
    val offices: List<OfficesModel> = emptyList(),
    val selected:Int=-1
)

interface AdminOfficesEvent {
    data class AdminOfficesSelected(val index: Int) : AdminOfficesEvent


}