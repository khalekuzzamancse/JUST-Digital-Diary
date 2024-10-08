package administration.controller_presenter.factory.add_officers

import administration.ui.admin.add_officers.OfficerFormController
import administration.controller_presenter.model.AdminOfficerModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class ValidatorImpl : OfficerFormController.Validator {
    private val _areAllFieldsFilled = MutableStateFlow(false)
    override val areMandatoryFieldFilled: StateFlow<Boolean> = _areAllFieldsFilled.asStateFlow()

    private val _errors = MutableStateFlow<List<String>>(emptyList())
    override val errors: StateFlow<List<String>> = _errors.asStateFlow()

    override fun observeFieldChanges(state: StateFlow<AdminOfficerModel>) {
        combine(state) {
            it.first()
        }.onEach { model ->
            val name = model.name
            val email = model.email
            val phone = model.phone
            _areAllFieldsFilled.update {
                name.isNotBlank() && email.isNotBlank() && phone.isNotBlank()
            }

            //Right now Need not  validation ,just check all mandatory field are filled or not

        }.launchIn(CoroutineScope(Dispatchers.Default))
    }
}