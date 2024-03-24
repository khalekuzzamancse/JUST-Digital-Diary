package administration.ui.offices.components.components.office_list.state

data class OfficeListState(
    val offices: List<Offices> = emptyList(),
    val selected:Int=-1
)
