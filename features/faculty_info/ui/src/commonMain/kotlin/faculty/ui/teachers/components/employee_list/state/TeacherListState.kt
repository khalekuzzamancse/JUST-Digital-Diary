package faculty.ui.teachers.components.employee_list.state

import faculty.ui.teachers.components.employee_list.state.Teacher

data class TeacherListState(
    val teachers:List<Teacher> = emptyList(),
)
