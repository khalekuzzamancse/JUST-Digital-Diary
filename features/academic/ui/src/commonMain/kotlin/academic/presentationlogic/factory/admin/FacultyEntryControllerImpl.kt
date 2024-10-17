@file:Suppress("functionName","propertyName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.FacultyEntryController
import academic.presentationlogic.controller.core.CoreControllerImpl
import academic.presentationlogic.model.FacultyWriteModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal open class FacultyEntryControllerImpl(
    override val validator: FacultyEntryController.Validator
) :
    FacultyEntryController, CoreControllerImpl() {
    protected val _faculty = MutableStateFlow(FacultyWriteModel("", ""))

    override val statusMessage = super._statusMessage.asStateFlow()
    override val isLoading = super._isLoading.asStateFlow()
    override val faculty = _faculty.asStateFlow()

    override fun onNameChanged(value: String) = _faculty.update { it.copy(name = value) }
    override fun onPriorityChanged(value: String) {
        val order = value.filter { it.isDigit() }
        _faculty.update { it.copy(priority = order) }
    }



}