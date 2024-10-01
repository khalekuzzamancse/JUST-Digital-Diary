@file:Suppress("unused")
package miscellaneous.controller_presenter.factory

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import miscellaneous.controller_presenter.controller.EventGalleryController
import miscellaneous.controller_presenter.ModelMapper
import miscellaneous.domain.exception.CustomException
import miscellaneous.domain.usecase.GetEventsUseCase
import miscellaneous.controller_presenter.model.GalleryEventModel

internal class EventGalleryControllerImpl(
private val useCase:GetEventsUseCase
) : EventGalleryController {
    private val _screenMessage = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _events = MutableStateFlow<List<GalleryEventModel>>(emptyList())

    override val isFetching = _isLoading
    override val screenMessage = _screenMessage.asStateFlow()

    override val events = _events.asStateFlow()

    override suspend fun fetch() {
        _startLoading()
        val result = useCase.execute()
        result.fold(
            onSuccess = { models ->
                _events.update {
                    models.map { ModelMapper.toUiModel(it) }
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