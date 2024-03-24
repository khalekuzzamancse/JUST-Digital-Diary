package administration.ui.offices.components.event


interface AdminOfficesEvent {
    data class AdminOfficesSelected(val index: Int) : AdminOfficesEvent


}