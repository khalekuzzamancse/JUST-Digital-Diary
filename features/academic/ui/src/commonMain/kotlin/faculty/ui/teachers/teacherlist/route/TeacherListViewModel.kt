package faculty.ui.teachers.teacherlist.route


import faculty.domain.teacher.repoisitory.TeacherListRepository
import faculty.ui.teachers.teacherlist.component.Teacher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TeacherListViewModel(
    private val repository: TeacherListRepository,
    private val deptId: String
) {
    private val _uiState = MutableStateFlow(TeacherListDestinationState())
    val uiState = _uiState.asStateFlow()

    suspend fun loadTeacherList() {
        startLoading()
        val result = repository.getTeachers(deptId)
        if (result.isSuccess) {
            _uiState.update { uiState ->
                val teacherListState =
                    uiState.teacherListState.copy(teachers = result.getOrDefault(emptyList()).map { model ->
                        Teacher(
                            name = model.name,
                            email = model.email,
                            additionalEmail = model.additionalEmail,
                            phone = model.phone,
                            achievements = model.achievements,
                            designations = model.designations,
                            deptName = model.deptName,
                            deptSortName = model.deptSortName,
                            roomNo = model.roomNo
                        )
                    })
                uiState.copy(
                    teacherListState = teacherListState
                )

            }

        }
        stopLoading()
    }
    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun stopLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }

}




