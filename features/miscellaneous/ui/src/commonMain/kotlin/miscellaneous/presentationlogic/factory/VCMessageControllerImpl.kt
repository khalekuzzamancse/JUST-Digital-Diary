@file:Suppress("unused")

package miscellaneous.presentationlogic.factory

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import miscellaneous.presentationlogic.ModelMapper
import miscellaneous.presentationlogic.controller.VCMessageController
import miscellaneous.domain.exception.CustomException
import miscellaneous.domain.usecase.GetVCInfoUseCase
import miscellaneous.presentationlogic.model.VCMessageModel

internal class VCMessageControllerImpl(
    private val useCase: GetVCInfoUseCase
) : VCMessageController {
    private val _screenMessage = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _vcMessage = MutableStateFlow<VCMessageModel?>(null)

    override val isFetching = _isLoading
    override val screenMessage = _screenMessage.asStateFlow()

    override val events = _vcMessage.asStateFlow()

    override suspend fun fetch() {
        _startLoading()
        val result = useCase.execute()
        result.fold(
            onSuccess = { model ->
                _vcMessage.update {
                    ModelMapper.toUiModel(model)

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