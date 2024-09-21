package faculty.ui.teachers

import faculty.ui.teachers.component.TeacherListState

data class TeacherListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val teacherListState: TeacherListState = TeacherListState(),
)
