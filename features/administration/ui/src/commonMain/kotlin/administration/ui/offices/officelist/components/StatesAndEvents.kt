package administration.ui.offices.officelist.components

data class OfficeListState(
    val offices: List<Offices> = emptyList(),
    val selected:Int=-1
)
data class Offices(
    val name:String,
    val numberOfSubOffices:String,
    val id:String,
)
interface AdminOfficesEvent {
    data class AdminOfficesSelected(val index: Int) : AdminOfficesEvent


}