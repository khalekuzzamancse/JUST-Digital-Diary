package faculty.ui.teachers.route.state

import faculty.ui.teachers.components.employee_list.state.TeacherListState

data class TeacherListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val teacherListState: TeacherListState = TeacherListState(),
)
