@file:Suppress("functionName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.TeacherEntryController
import academic.presentationlogic.controller.admin.UpdateTeacherController
import academic.presentationlogic.ModelMapper
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
                .execute(
                    teacherId,
                    with(ModelMapper) { _teacherState.value.toDomainModelOrThrow() })
                .showStatusMsg(operation = "Update")

            super.stopLoading()

        } catch (_: Exception) {
            "Priority must be an integer".showAsErrorMsg()
        }
    }

    private suspend fun _readTeacher() {
        //Can throw exception when try to convert string to integer in model mapper
        try {
            super.startLoading()
            readTeacherUseCase
                .execute(teacherId)
                .fold(
                    onSuccess = { model ->
                        _teacherState.update { with(ModelMapper) { model.toEntryModel() } }
                        try {
                            //TODO: Have a problem to data layer, dept  id is not loaded
                            // TODO: Refactor later, Edge case: all dept may not be leaded yet...
                            super.onDeptChange(
                                super.dept.value
                                    .map { it.id }
                                    .indexOf(model.deptId)
                            )
                        } catch (_: Exception) {

                        }
                    },
                    onFailure = { exception ->
                        exception.showStatusMsg(optionalMsg = "Unable to load teacher")
                    }
                )
            super.stopLoading()
        } catch (_: Exception) {
            "Priority must be an integer".showAsErrorMsg()
        }
    }

}