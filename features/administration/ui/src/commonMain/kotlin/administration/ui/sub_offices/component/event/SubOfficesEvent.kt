package administration.ui.sub_offices.component.event

interface SubOfficesEvent {
    data class SubOfficeSelected(val index: Int) : SubOfficesEvent

}