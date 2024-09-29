package administration.ui.offices

import administration.controller_presenter.model.OfficeModel

data class OfficeListState(
    val offices: List<OfficeModel> = emptyList(),
    val selected:Int=-1
)

interface AdminOfficesEvent {
    data class AdminOfficesSelected(val index: Int) : AdminOfficesEvent


}