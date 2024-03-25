package faculty.ui.teachers.teacherlist.route

import faculty.ui.teachers.teacherlist.component.TeacherListState

data class TeacherListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val teacherListState: TeacherListState = TeacherListState(),
)
