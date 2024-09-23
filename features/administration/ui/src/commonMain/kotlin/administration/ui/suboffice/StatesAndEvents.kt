package administration.ui.suboffice

import administration.ui.model.SubOfficeModel

data class SubOfficeListState(
    val title: String? = null,
    val enableBackNavigation: Boolean = true,
    val subOffices: List<SubOfficeModel> = emptyList(),
    val selected: Int = -1
)

interface SubOfficesEvent {
    data class SubOfficeSelected(val index: Int) : SubOfficesEvent

}