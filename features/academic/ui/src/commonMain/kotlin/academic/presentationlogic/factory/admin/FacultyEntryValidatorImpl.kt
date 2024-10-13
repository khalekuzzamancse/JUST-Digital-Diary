package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.FacultyAdminBaseController
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

internal class FacultyEntryValidatorImpl(

) : FacultyAdminBaseController.Validator {

    private val _fieldsFilled = MutableStateFlow(false)
    private val _errors = MutableStateFlow(emptyList<String>())

    override val areMandatoryFieldFilled = _fieldsFilled.asStateFlow()
    override val errors = _errors.asStateFlow()


    override fun activate(state: StateFlow<FacultyEntryModel>) {
        combine(state) {
            it.first()
        }.onEach { model ->
            val name = model.name
            val id = model.priority
            // Just check if all mandatory fields are filled or not
            _fieldsFilled.update {
                id.isNotBlank() && name.isNotBlank()
            }
        }.launchIn(CoroutineScope(Dispatchers.Default))
    }
}
