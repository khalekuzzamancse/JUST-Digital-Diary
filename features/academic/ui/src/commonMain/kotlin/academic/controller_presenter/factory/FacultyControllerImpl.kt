@file:Suppress("unused", "FunctionName")

package academic.controller_presenter.factory

import academic.controller_presenter.controller.FacultyController
import academic.controller_presenter.mapper.ModelMapper
import academic.controller_presenter.model.FacultyModel
import faculty.domain.exception.CustomException
import faculty.domain.usecase.RetrieveFactualityUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class FacultyControllerImpl(
    private val userCase: RetrieveFactualityUseCase,
) : FacultyController {
    private val _screenMessage = MutableStateFlow<String?>(null)
    private val _faculties = MutableStateFlow<List<FacultyModel>>(emptyList())
    private val _isLoading = MutableStateFlow(false)
    private val _selected = MutableStateFlow<Int?>(null)

    override val errorMessage=_screenMessage.asStateFlow()
    override val isFetching = _isLoading.asStateFlow()
    override val faculties = _faculties.asStateFlow()
    override val selected = _selected.asStateFlow()
    override fun onSelected(index: Int?) {
        _selected.update { index }

    }


    override suspend fun fetchFaculty() {
        _startLoading()
        val result = userCase.execute()
        result.fold(
            onSuccess = { models ->
                _faculties.update {
                    models
                        .map { ModelMapper.toUiFacultyModel(it) }
                }
            },
            onFailure = { exception ->

                when (exception) {
                    is CustomException -> {
                        println("Debug exception:${exception.debugMessage}")
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

    private fun _startLoading() = _isLoading.update { true }
    private fun _stopLoading() = _isLoading.update { false }
    private fun _updateErrorMessage(msg: String) {
        CoroutineScope(Dispatchers.Default).launch {
            _screenMessage.update { msg }
            //clear after 4 seconds
            delay(4_000)
            _screenMessage.update { null }
        }
    }
}