@file:Suppress("functionName")
package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.TeacherEntryController
import academic.presentationlogic.controller.admin.UpdateTeacherController
import academic.presentationlogic.mapper.AdminModelMapper
import core.customexception.CustomException
import faculty.domain.usecase.admin.ReadAllDepartmentUseCase
import faculty.domain.usecase.admin.ReadTeacherUseCase
import faculty.domain.usecase.admin.UpdateTeacherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class UpdateTeacherControllerImpl(
    private val teacherId: String,
    private val readTeacherUseCase: ReadTeacherUseCase,
    private val writeUseCase: UpdateTeacherUseCase,
    readAllDeptUseCase: ReadAllDepartmentUseCase,
    validator: TeacherEntryController.Validator,
) : TeacherEntryControllerImpl(allDeptUseCase = readAllDeptUseCase, validator = validator),
    UpdateTeacherController {

    init {
        super.validator.observeFieldChanges(state = teacherState)
        CoroutineScope(Dispatchers.Default).launch {
            super.readAllDept()
        }
        CoroutineScope(Dispatchers.Default).launch {
            _readTeacher()
        }
    }

    override suspend fun update() {
        //Can throw exception when try to convert string to integer in model mapper
        try {
            super.startLoading()
            writeUseCase
                .execute(teacherId,with(AdminModelMapper) { _teacherState.value.toDomainModelOrThrow() })
                .fold(
                    onSuccess = {
                        super.updateErrorMessage("Added")
                    },
                    onFailure = { exception ->
                        when (exception) {
                            is CustomException -> super.updateErrorMessage(exception.message)
                            else -> super.updateErrorMessage("Failed insert")
                        }
                    }
                )
            super.stopLoading()
        } catch (_: Exception) {
            super.updateErrorMessage("Failed,Make sure priority field as integer")
        }
    }

    private suspend fun _readTeacher() {
        //Can throw exception when try to convert string to integer in model mapper
        try {
            super.startLoading()
            readTeacherUseCase
                .execute(teacherId)
                .fold(
                    onSuccess = { domainModel ->
                        val uiModel = with(AdminModelMapper) { domainModel.toUIModel() }
                        _teacherState.update { uiModel }
                        try {
                            //TODO: Have a problem to data layer, dept  id is not loaded
                            // TODO: Refactor later, Edge case: all dept may not be leaded yet...
                            super.onDeptChange(
                                super.dept.value
                                    .map { it.id }
                                    .indexOf(domainModel.deptId)
                            )
                        } catch (_: Exception) {

                        }
                    },
                    onFailure = { exception ->
                        when (exception) {
                            is CustomException -> super.updateErrorMessage(exception.message)
                            else -> super.updateErrorMessage("Failed read the teacher")
                        }
                    }
                )
            super.stopLoading()
        } catch (_: Exception) {
            super.updateErrorMessage("Failed,Make sure priority field as integer")
        }
    }

}