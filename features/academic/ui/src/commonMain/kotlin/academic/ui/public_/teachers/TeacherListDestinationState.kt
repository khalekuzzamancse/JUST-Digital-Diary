package academic.ui.public_.teachers

import academic.model.TeacherModel


data class TeacherListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val teachers: List<TeacherModel> = emptyList()
)
