@file:Suppress("FunctionName")

package administration.controller_presenter.factory

import admin_office.domain.exception.CustomException
import admin_office.domain.usecase.RetrieveOfficeListUseCase
import administration.ui.public_.Mapper
import administration.controller_presenter.controller.OfficeController
import administration.controller_presenter.model.OfficeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OfficeControllerImpl(
    private val userCase: RetrieveOfficeListUseCase,
): OfficeController {
    private val _screenMessage = MutableStateFlow<String?>(null)
    private val _offices = MutableStateFlow<List<OfficeModel>>(emptyList())
    private val _isLoading = MutableStateFlow(false)
    private val _selected = MutableStateFlow<Int?>(null)

    //
    override val errorMessage=_screenMessage.asStateFlow()
    override val isFetching = _isLoading.asStateFlow()
    override val offices = _offices.asStateFlow()
    override val selected = _selected.asStateFlow()
    override fun onSelected(index: Int?) {
        _selected.update { index }

    }


     override suspend fun fetch() {
        _startLoading()
        val result = userCase.execute()
        result.fold(
            onSuccess = { models ->
                _offices.update {
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