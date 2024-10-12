@file:Suppress("functionName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.FacultyEntryController
import academic.presentationlogic.mapper.ModelMapper
import academic.presentationlogic.model.admin.FacultyEntryModel
import faculty.domain.exception.CustomException
import faculty.domain.usecase.admin.AddFacultyUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class FacultyEntryControllerImpl(
    private val useCase: AddFacultyUseCase
) : FacultyEntryController {
    private val _networkIOInProgress = MutableStateFlow(false)
    private val _statusMessage = MutableStateFlow<String?>(null)

    private val _faculty = MutableStateFlow(FacultyEntryModel("", ""))


    override val statusMessage = _statusMessage.asStateFlow()
    override val networkIOInProgress = _networkIOInProgress.asStateFlow()
    override val faculty = _faculty.asStateFlow()


    override val validator: FacultyEntryController.Validator =
        object : FacultyEntryController.Validator {

            private val _fieldsFilled = MutableStateFlow(false)
            private val _errors = MutableStateFlow(emptyList<String>())
            override val areMandatoryFieldFilled = _fieldsFilled.asStateFlow()
            override val errors = _errors.asStateFlow()
            override fun observeFieldChanges(state: StateFlow<FacultyEntryModel>) {
                combine(state) {
                    it.first()
                }.onEach { model ->
                    val name = model.name
                    val id = model.priority
                    //Right now Need not  validation ,just check all mandatory field are filled or not
                    _fieldsFilled.update {
                        id.isNotBlank() && name.isNotBlank()
                    }
                }.launchIn(CoroutineScope(Dispatchers.Default))

            }

        }

    init {
        validator.observeFieldChanges(faculty)
    }


    override fun onNameChanged(value: String) = _faculty.update { it.copy(name = value) }
    override fun onPriorityChanged(value: String) {
        val order = value.filter { it.isDigit() }
        _faculty.update { it.copy(priority = order) }


    }

    override suspend fun onAddRequest() {
        val model = with(ModelMapper) { faculty.value.toDomainModelOrThrow() }
        _onNetworkIOStart()
        val result = useCase.execute(model)
        result.fold(
            onSuccess = {
                _updateErrorMessage("Added Successfully")

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
        _onNetworkIOStop()
    }

    override suspend fun onUpdateRequest() {
        _onNetworkIOStart()

        _onNetworkIOStop()
    }


    private fun _onNetworkIOStart() = _networkIOInProgress.update { true }
    private fun _onNetworkIOStop() = _networkIOInProgress.update { false }
    private fun _updateErrorMessage(msg: String) {
        CoroutineScope(Dispatchers.Default).launch {
            _statusMessage.update { msg }
            //clear after 4 seconds
            delay(4_000)
            _statusMessage.update { null }
        }
    }
}