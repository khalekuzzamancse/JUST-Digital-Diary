package administration.ui.sub_offices.component.component.sub_office_list.state

import administration.ui.sub_offices.component.component.sub_office_list.state.SubOffice

data class SubOfficeListState(
    val title: String? = null,
    val enableBackNavigation: Boolean = true,
    val subOffices: List<SubOffice> = emptyList(),
    val selected: Int = -1
)