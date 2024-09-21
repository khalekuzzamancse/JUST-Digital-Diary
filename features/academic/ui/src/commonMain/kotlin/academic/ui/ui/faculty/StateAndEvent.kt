package faculty.ui.faculty

data class FacultyListState(
    val faculties: List<Faculty> = emptyList(),
    val selected:Int?=null
)
data class Faculty(
    val name:String,
    val numberOfDepartment:String,
    val id:String,
)
interface FacultyListEvent {
    data class FacultySelected(val index: Int) : FacultyListEvent
}
