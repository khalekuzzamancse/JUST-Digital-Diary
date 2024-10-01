package profile.presentationlogic.factory

import domain.execption.CustomException
import domain.usecase.GetProfileUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import profile.presentationlogic.controller.ProfileController
import profile.presentationlogic.model.ModelMapper
import profile.presentationlogic.model.ProfileModel

class ProfileControllerImpl(
    private val useCase: GetProfileUseCase
) : ProfileController {
    private val _screenMessage = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(false)
    private val _profile = MutableStateFlow(toEmpty())

    override val isFetching = _isLoading
    override val screenMessage = _screenMessage.asStateFlow()
    override val profile = _profile.asStateFlow()


    override suspend fun fetch() {
        _startLoading()
        val result = useCase.execute()
        result.fold(
            onSuccess = { model ->
                _profile.update {
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

    private fun toEmpty() = ProfileModel("", "", "")
}