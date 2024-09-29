package administration.factory.add_officers

import administration.ui.admin.add_officers.OfficerFormController
import administration.controller_presenter.model.AdminOfficerModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


/**
 * Private implementation of the TeacherFormController interface.
 * Manages the state of TeacherModel using MutableStateFlow and responds to events.
 */
internal class OfficerFormControllerImpl(
    override val validator: OfficerFormController.Validator
) : OfficerFormController {
    private val _state = MutableStateFlow(_emptyState())
    override val state: StateFlow<AdminOfficerModel> = _state.asStateFlow()
    override fun onNameChange(value: String) {
        _state.value = _state.value.copy(name = value)
    }

    override fun onEmailChange(value: String) {
        _state.value = _state.value.copy(email = value)
    }

    override fun onAdditionalEmailChange(value: String) {
        _state.value = _state.value.copy(additionalEmail = value)
    }

    override fun onAchievementsChange(value: String) {
        _state.value = _state.value.copy(achievements = value)
    }

    override fun onPhoneChange(value: String) {
        _state.value = _state.value.copy(phone = value)
    }

    override fun onDesignationsChange(value: String) {
        _state.value = _state.value.copy(designations = value)
    }


    override fun onRoomNoChange(value: Int) {
        _state.value = _state.value.copy(roomNo = value.toString())
    }

    init {
        validator.observeFieldChanges(state = state)
    }


    //TODO:Helper methods section
    private fun _emptyState() = AdminOfficerModel(
        name = "",
        email = "",
        additionalEmail = "",
        achievements = "",
        phone = "",
        designations = "",
        roomNo = ""
    )
}