package faculty.ui.teachers.teacherlist.component

data class TeacherListState(
    val teachers:List<Teacher> = emptyList(),
)

data class Teacher(
    val name: String,
    val email: String,
    val additionalEmail: String,
    val profileImageLink: String="https://static.just.edu.bd/images/public/teachers/1603270274986_900.jpeg",
    val achievements: String,
    val phone: String,
    val designations: String,
    val deptName: String,
    val deptSortName: String,
    val roomNo: String,
)

interface TeacherListEvent {
    data class CallRequest(val number: String) : TeacherListEvent
    data class MessageRequest(val number: String) : TeacherListEvent
    data class EmailRequest(val email: String) : TeacherListEvent


}