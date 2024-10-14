package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.DeptEntryController
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

internal class DepartmentEntryValidatorImpl: DeptEntryController.Validator {

    private val _fieldsFilled = MutableStateFlow(false)
    private val _errors = MutableStateFlow(emptyList<String>())
    override val areMandatoryFieldFilled = _fieldsFilled.asStateFlow()
    override val errors = _errors.asStateFlow()
    override fun observeFieldChanges(state: StateFlow<DepartmentEntryModel>) {
        combine(state) {
            it.first()
        }.onEach { model ->
            val name = model.name
            val id = model.priority
            val shortName = model.shortName
            //Right now Need not  validation ,just check all mandatory field are filled or not
            _fieldsFilled.update {
                id.isNotBlank() && name.isNotBlank() && shortName.isNotBlank()
            }
        }.launchIn(CoroutineScope(Dispatchers.Default))

    }
}