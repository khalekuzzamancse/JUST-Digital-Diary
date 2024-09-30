@file:Suppress("FunctionName")
package administration.controller_presenter.factory

import admin_office.domain.exception.CustomException
import admin_office.domain.usecase.RetrieveOfficersUseCase
import administration.ui.public_.Mapper
import administration.controller_presenter.controller.EmployeeListController
import administration.controller_presenter.model.AdminOfficerModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmployeeListControllerImpl(
    private val useCase:RetrieveOfficersUseCase
): EmployeeListController {
    private val _screenMessage = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _employees = MutableStateFlow<List<AdminOfficerModel>>(emptyList())


    override val errorMessage=_screenMessage.asStateFlow()
    override val isFetching = _isLoading.asStateFlow()
    override val employees=_employees.asStateFlow()


    override suspend fun fetch(subOfficeId: String) {
        _startLoading()
        val result = useCase.execute(subOfficeId)
        result.fold(
            onSuccess = { models ->
                _employees.update {
                    models
                        .map { Mapper.toUiModel(it) }
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