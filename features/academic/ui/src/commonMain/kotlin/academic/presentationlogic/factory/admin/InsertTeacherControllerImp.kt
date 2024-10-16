package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.InsertTeacherController
import academic.presentationlogic.controller.admin.TeacherEntryController
import academic.presentationlogic.mapper.AdminModelMapper
import core.customexception.CustomException
import faculty.domain.usecase.admin.InsertTeacherUseCase
import faculty.domain.usecase.admin.ReadAllDepartmentUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Private implementation of the TeacherFormController interface.
 * Manages the state of TeacherModel using MutableStateFlow and responds to events.
 */
internal class InsertTeacherControllerImp(
    readUseCase: ReadAllDepartmentUseCase,
    validator: TeacherEntryController.Validator,
    private val writeUseCase: InsertTeacherUseCase,
) : TeacherEntryControllerImpl(allDeptUseCase = readUseCase, validator = validator), InsertTeacherController {

    override suspend fun insert() {
        //Can throw exception when try to convert string to integer in model mapper
        try {
            println("Request")
            super.startLoading()
            writeUseCase
                .execute(with(AdminModelMapper) { _teacherState.value.toDomainModelOrThrow() })
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

    init {
        super.validator.observeFieldChanges(state = teacherState)
        CoroutineScope(Dispatchers.Default).launch {
            super.readAllDept()
        }
    }

}