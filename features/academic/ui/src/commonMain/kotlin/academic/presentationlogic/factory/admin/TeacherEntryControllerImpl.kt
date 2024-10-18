@file:Suppress("propertyName", "functionName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.TeacherEntryController
import academic.presentationlogic.controller.core.CoreController
import academic.presentationlogic.ModelMapper
import academic.presentationlogic.model.TeacherWriteModel
import academic.presentationlogic.model.DepartmentReadModel
import faculty.domain.usecase.admin.ReadAllDepartmentUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


/**
 * Private implementation of the TeacherFormController interface.
 * Manages the state of TeacherModel using MutableStateFlow and responds to events.
 */
internal open class TeacherEntryControllerImpl(
    override val validator: TeacherEntryController.Validator,
    private val allDeptUseCase: ReadAllDepartmentUseCase,
) : TeacherEntryController, CoreController() {

    protected val _teacherState = MutableStateFlow(TeacherWriteModel.empty())
    private val _departments = MutableStateFlow<List<DepartmentReadModel>>(emptyList())
    private val _selectedDeptIndex = MutableStateFlow<Int?>(null)

    override val dept = _departments.asStateFlow()
    override val selectedDeptIndex = _selectedDeptIndex.asStateFlow()

    override val statusMessage = super._statusMessage.asStateFlow()
    override val isLoading = super._isLoading.asStateFlow()

    override val teacherState: StateFlow<TeacherWriteModel> = _teacherState.asStateFlow()
    override fun onNameChange(value: String) {
        _teacherState.value = _teacherState.value.copy(name = value)
    }

    override fun onEmailChange(value: String) {
        _teacherState.value = _teacherState.value.copy(email = value)
    }

    override fun onAdditionalEmailChange(value: String) {
        _teacherState.value = _teacherState.value.copy(additionalEmail = value)
    }

    override fun onAchievementsChange(value: String) {
        _teacherState.value = _teacherState.value.copy(achievements = value)
    }

    override fun onPhoneChange(value: String) {
        _teacherState.value = _teacherState.value.copy(phone = value)
    }

    override fun onDesignationsChange(value: String) {
        _teacherState.value = _teacherState.value.copy(designations = value)
    }

    override fun onDeptChange(index: Int) {
        _selectedDeptIndex.update { index }
        try {//Since accessing index, taking double safety
            _teacherState.update { it.copy(deptId = dept.value[index].id) }
        } catch (_: Exception) {

        }

    }

    override fun onRoomNoChange(value: String) {
        _teacherState.value = _teacherState.value.copy(roomNo = value)
    }

    override fun onIdChange(value: String) {

        _teacherState.value = _teacherState.value.copy(priority = value.filter { it.isDigit() })


    }

    override fun onImageLinkChange(value: String) =
        _teacherState.update { it.copy(profileImageLink = value) }

    protected suspend fun readAllDept() {
        super.startLoading()
        allDeptUseCase
            .execute()
            .fold(
                onSuccess = { models ->
                    with(ModelMapper){
                        models.map { it.toModel() }
                    }
                },
                onFailure = { ex -> ex.showStatusMsg(optionalMsg = "Unable to load departments") }
            )
        super.stopLoading()
    }


}