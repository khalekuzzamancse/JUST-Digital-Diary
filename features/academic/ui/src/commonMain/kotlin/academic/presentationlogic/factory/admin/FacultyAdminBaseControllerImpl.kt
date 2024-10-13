@file:Suppress("functionName","propertyName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.FacultyAdminBaseController
import academic.presentationlogic.controller.admin.UiCommonStateController
import academic.presentationlogic.model.admin.FacultyEntryModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal open class FacultyAdminBaseControllerImpl(
    override val validator: FacultyAdminBaseController.Validator
) :
    FacultyAdminBaseController, UiCommonStateController() {
    protected val _faculty = MutableStateFlow(FacultyEntryModel("", ""))

    override val statusMessage = super._statusMessage.asStateFlow()
    override val networkIOInProgress = super._networkIOInProgress.asStateFlow()
    override val faculty = _faculty.asStateFlow()

    override fun onNameChanged(value: String) = _faculty.update { it.copy(name = value) }
    override fun onPriorityChanged(value: String) {
        val order = value.filter { it.isDigit() }
        _faculty.update { it.copy(priority = order) }
    }



}