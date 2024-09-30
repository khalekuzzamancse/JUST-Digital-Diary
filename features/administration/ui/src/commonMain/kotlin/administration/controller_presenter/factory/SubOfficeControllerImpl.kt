@file:Suppress("FunctionName")

package administration.controller_presenter.factory

import admin_office.domain.exception.CustomException
import admin_office.domain.usecase.RetrieveSubOfficeListUseCase
import administration.ui.public_.Mapper
import administration.controller_presenter.controller.SubOfficeController
import administration.controller_presenter.model.SubOfficeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SubOfficeControllerImpl(
    private val useCase: RetrieveSubOfficeListUseCase
) : SubOfficeController {
    private val _screenMessage = MutableStateFlow<String?>(null)
    private val _subOffices = MutableStateFlow<List<SubOfficeModel>>(emptyList())
    private val _isLoading = MutableStateFlow(false)
    private val _selected = MutableStateFlow<Int?>(null)

    override val errorMessage = _screenMessage.asStateFlow()
    override val isFetching = _isLoading.asStateFlow()
    override val sobOffices = _subOffices.asStateFlow()
    override val selected = _selected.asStateFlow()
    override fun onSelected(index: Int?) {
        _selected.update { index }

    }

    override suspend fun fetch(officeId: String) {
        _startLoading()
        val result = useCase.execute(officeId)
        result.fold(
            onSuccess = { models ->
                _subOffices.update {
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