package academic.ui.non_admin.teachers

import academic.ui.model.TeacherModel


data class TeacherListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val teachers: List<TeacherModel> = emptyList()
)
