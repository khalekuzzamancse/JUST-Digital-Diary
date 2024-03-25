package administration.ui.suboffice.subofficelist
data class SubOfficeListState(
    val title: String? = null,
    val enableBackNavigation: Boolean = true,
    val subOffices: List<SubOffice> = emptyList(),
    val selected: Int = -1
)
class SubOffice(
    val name: String,
    val employeeCnt: String,
    val id: String,
)
interface SubOfficesEvent {
    data class SubOfficeSelected(val index: Int) : SubOfficesEvent

}