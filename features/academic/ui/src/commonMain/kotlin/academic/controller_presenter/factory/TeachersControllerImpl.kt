@file:Suppress("unused", "FunctionName")

package academic.controller_presenter.factory

import academic.controller_presenter.model.TeacherModel
import academic.controller_presenter.mapper.ModelMapper
import academic.controller_presenter.controller.TeachersController
import faculty.domain.exception.CustomException
import faculty.domain.usecase.RetrieveTeachersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class TeachersControllerImpl(
    private val useCase: RetrieveTeachersUseCase,
    private val token: String?,
) : TeachersController {
    private val _isFetching = MutableStateFlow(false)
    private val _screenMessage = MutableStateFlow<String?>(null)
    private val _teachers = MutableStateFlow<List<TeacherModel>>(emptyList())

    override val isFetching = _isFetching.asStateFlow()
    override val teachers = _teachers.asStateFlow()
    override val errorMessage=_screenMessage.asStateFlow()

    override suspend fun fetch(deptId: String) {
        _startLoading()
        val result = useCase.execute(deptId, token = token)
        result.fold(
            onSuccess = { models ->
                _teachers.update {
                    models
                        .map { ModelMapper.toTeacherUiModel(it) }
                }
            },
            onFailure = { exception ->

                when (exception) {
                    is CustomException -> {
                        _updateErrorMessage(exception.message)
                    }

                    else -> {
                        _updateErrorMessage("Something went wrong")
                    }

                }
            }
        )
        _stopLoading()
    }

    private fun _startLoading() = _isFetching.update { true }
    private fun _stopLoading() = _isFetching.update { false }
    private fun _updateErrorMessage(msg: String) {
        CoroutineScope(Dispatchers.Default).launch {
            _screenMessage.update { msg }
            //clear after 4 seconds
            delay(4_000)
            _screenMessage.update { null }
        }
    }
}