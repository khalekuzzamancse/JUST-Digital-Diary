@file:Suppress("unused", "FunctionName")

package academic.factory

import academic.model.TeacherModel
import academic.presenter.Presenter
import academic.ui.public_.teachers.TeachersController
import faculty.domain.usecase.RetrieveTeachersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class TeachersControllerImpl (
    private val useCase: RetrieveTeachersUseCase
) : TeachersController {
    private val _isFetching = MutableStateFlow(false)
    override val isFetching = _isFetching.asStateFlow()
    private val _teachers = MutableStateFlow<List<TeacherModel>>(emptyList())
    override val teachers = _teachers.asStateFlow()

    override suspend fun fetch(deptId: String) {
        _startLoading()
        val result = useCase.execute(deptId)
        result.fold(
            onSuccess = { models ->
                _teachers.update {
                    models
                        .map { Presenter.toTeacherUiModel(it) }
                }
            },
            onFailure = {

            }
        )
        _stopLoading()
    }

    private fun _startLoading() = _isFetching.update { true }
    private fun _stopLoading() = _isFetching.update { false }
}