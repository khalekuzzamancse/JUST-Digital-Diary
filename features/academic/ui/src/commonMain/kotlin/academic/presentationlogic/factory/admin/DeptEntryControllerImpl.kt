@file:Suppress("functionName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.DepartmentEntryController
import academic.presentationlogic.model.admin.DepartmentEntryModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

internal class DeptEntryControllerImpl : DepartmentEntryController {
    private val _networkIOInProgress = MutableStateFlow(false)
    private val _dept = MutableStateFlow(DepartmentEntryModel("", "", "",""))
    private val _statusMessage = MutableStateFlow<String?>(null)


    override val networkIOInProgress = _networkIOInProgress.asStateFlow()
    override val dept = _dept.asStateFlow()
    override val statusMessage = _statusMessage.asStateFlow()


    override val validator = object : DepartmentEntryController.Validator {

        private val _fieldsFilled = MutableStateFlow(false)
        private val _errors = MutableStateFlow(emptyList<String>())
        override val areMandatoryFieldFilled = _fieldsFilled.asStateFlow()
        override val errors = _errors.asStateFlow()
        override fun observeFieldChanges(state: StateFlow<DepartmentEntryModel>) {
            combine(state) {
                it.first()
            }.onEach { model ->
                val name = model.name
                val id = model.id
                val shortName = model.shortName
                //Right now Need not  validation ,just check all mandatory field are filled or not
                _fieldsFilled.update {
                    id.isNotBlank() && name.isNotBlank() && shortName.isNotBlank()
                }
            }.launchIn(CoroutineScope(Dispatchers.Default))

        }

    }

    init {
        validator.observeFieldChanges(dept)
    }

    override fun onIdChanged(value: String) = _dept.update { it.copy(id = value) }
    override fun onNameChanged(value: String) = _dept.update { it.copy(name = value) }
    override fun onShortNameChanged(value: String) = _dept.update { it.copy(shortName = value) }
    override fun onOrderChanged(value: String) {
        val order = value.filter { it.isDigit() }
        _dept.update { it.copy(order = order) }
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