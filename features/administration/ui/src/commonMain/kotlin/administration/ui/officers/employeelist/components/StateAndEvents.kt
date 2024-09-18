package administration.ui.officers.employeelist.components

data class AdminOfficerListState(
    val adminOfficers:List<AdminOfficer> = emptyList()
)
data class AdminOfficer(
    val name: String,
    val email: String,
    val additionalEmail: String,
    val profileImageLink: String="https://static.just.edu.bd/images/public/teachers/1603270274986_900.jpeg",
    val achievements: String,
    val phone: String,
    val designations: String,
    val roomNo: String,
)
interface AdminEmployeeListEvent {
    data class CallRequest(val number:String): AdminEmployeeListEvent
    data class MessageRequest(val number:String): AdminEmployeeListEvent
    data class EmailRequest(val email:String): AdminEmployeeListEvent

}