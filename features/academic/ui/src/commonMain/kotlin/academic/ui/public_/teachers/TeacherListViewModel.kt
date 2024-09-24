package academic.ui.public_.teachers


import academic.model.Dept
import academic.model.TeacherModel
import faculty.domain.usecase.RetrieveTeachersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TeacherListViewModel(
    private val useCase: RetrieveTeachersUseCase,
) {
    private val _uiState = MutableStateFlow(TeacherListDestinationState())
    val uiState = _uiState.asStateFlow()

    suspend fun loadTeacherList() {
        startLoading()
        val result = useCase.execute("")
        if (result.isSuccess) {
            _uiState.update { uiState ->
                val teacherListState =
                    uiState.copy(teachers = result.getOrDefault(emptyList()).map { model ->
                        TeacherModel(
                            name = model.name,
                            email = model.email,
                            additionalEmail = model.additionalEmail,
                            phone = model.phone,
                            achievements = model.achievements,
                            designations = model.designations,
                            dept = Dept(
                                model.deptName,
                                model.deptSortName
                            ),
                            roomNo = model.roomNo
                        )
                    })
                uiState.copy(
                    teachers = teacherListState.teachers
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




