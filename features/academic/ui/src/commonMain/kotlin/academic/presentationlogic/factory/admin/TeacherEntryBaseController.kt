@file:Suppress("propertyName","functionName")
package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.TeacherEntryController
import academic.presentationlogic.controller.core.CoreControllerImpl
import academic.presentationlogic.mapper.PublicModelMapper
import academic.presentationlogic.model.admin.TeacherEntryModel
import academic.presentationlogic.model.public_.DepartmentModel
import faculty.domain.exception.CustomException
import faculty.domain.usecase.admin.ReadAllDepartmentUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


/**
 * Private implementation of the TeacherFormController interface.
 * Manages the state of TeacherModel using MutableStateFlow and responds to events.
 */
internal open class TeacherEntryBaseController(
    override val validator: TeacherEntryController.Validator,
    private val allDeptUseCase: ReadAllDepartmentUseCase,
) : TeacherEntryController, CoreControllerImpl() {

    protected val _teacherState = MutableStateFlow(_emptyState())
    private val _departments = MutableStateFlow<List<DepartmentModel>>(emptyList())
    private val _selectedDeptIndex=MutableStateFlow<Int?>(null)

    override val statusMessage = _statusMessage.asStateFlow()
    override val dept = _departments.asStateFlow()
    override val selectedDeptIndex=_selectedDeptIndex.asStateFlow()

    override val isLoading = _isLoading.asStateFlow()
    override val teacherState: StateFlow<TeacherEntryModel> = _teacherState.asStateFlow()
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

    protected suspend fun readAllDept() {
        super.startLoading()
        allDeptUseCase
            .execute()
            .fold(
                onSuccess = { models ->
                    with(PublicModelMapper){
                        _departments.update { models.map { toUiFacultyModel(it) } }
                    }

                },
                onFailure = { exception ->
                    when (exception) {
                        is CustomException -> {
                            super.updateErrorMessage(exception.message)
                        }

                        else -> {
                            super.updateErrorMessage("Failed to load faculties")
                        }

                    }
                }
            )
        super.stopLoading()
    }



    //TODO:Helper methods section
    private fun _emptyState() = TeacherEntryModel(
        name = "",
        email = "",
        additionalEmail = "",
        achievements = "",
        phone = "",
        deptId = "",
        roomNo ="",
        designations = "",
        priority = ""
    )

}