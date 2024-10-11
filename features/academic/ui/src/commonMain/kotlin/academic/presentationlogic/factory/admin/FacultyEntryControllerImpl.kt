@file:Suppress("functionName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.FacultyEntryController
import academic.presentationlogic.model.admin.FacultyEntryModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

internal class FacultyEntryControllerImpl : FacultyEntryController {
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
        _onNetworkIOStart()

        _onNetworkIOStop()
    }

    override suspend fun onUpdateRequest() {
        _onNetworkIOStart()

        _onNetworkIOStop()
    }


    private fun _onNetworkIOStart() = _networkIOInProgress.update { true }
    private fun _onNetworkIOStop() = _networkIOInProgress.update { false }
}