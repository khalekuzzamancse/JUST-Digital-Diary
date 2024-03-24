package faculty.ui.faculty.components.faculty_list

data class FacultyListState(
    val faculties: List<Faculty> = emptyList(),
    val selected:Int?=null
)
