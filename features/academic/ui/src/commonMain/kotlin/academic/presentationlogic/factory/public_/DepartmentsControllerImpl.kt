@file:Suppress("unused", "FunctionName")

package academic.presentationlogic.factory.public_

import academic.presentationlogic.controller.public_.DepartmentController
import academic.presentationlogic.mapper.ModelMapper
import academic.presentationlogic.model.public_.DepartmentModel
import faculty.domain.exception.CustomException
import faculty.domain.usecase.public_.RetrieveDepartmentsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class DepartmentsControllerImpl(
    private val userCase: RetrieveDepartmentsUseCase,
) : DepartmentController {
    private val _screenMessage = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _departments = MutableStateFlow<List<DepartmentModel>>(emptyList())
    private val _selected = MutableStateFlow<Int?>(null)
    override val errorMessage=_screenMessage.asStateFlow()


    //TODO
    override val isFetching = _isLoading.asStateFlow()
    override val departments = _departments.asStateFlow()
    override val selected = _selected.asStateFlow()
    override fun onSelected(index: Int) {
        _selected.update { index }

    }

    override suspend fun fetchDepartments(facultyId: String) {
        _startLoading()
        val result = userCase.execute(facultyId = facultyId)
        result.fold(
            onSuccess = { models ->
                _departments.update {
                    models
                        .map { ModelMapper.toUiFacultyModel(it) }
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