package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.InsertTeacherController
import academic.presentationlogic.controller.admin.TeacherEntryController
import academic.presentationlogic.mapper.AdminModelMapper
import common.ui.SnackBarMessage
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
) : TeacherEntryControllerImpl(allDeptUseCase = readUseCase, validator = validator),
    InsertTeacherController {

    override suspend fun insert() {
        //Can throw exception when try to convert string to integer in model mapper
        try {
            println("Request")
            super.startLoading()
            writeUseCase
                .execute(with(AdminModelMapper) { _teacherState.value.toDomainModelOrThrow() })
                .updateStatusMsg(operationName = "Insertion")
            super.stopLoading()
        } catch (_: Exception) {
            "Failed,Make sure priority field as integer".updateAsErrorMessage()
        }
    }

    init {
        super.validator.observeFieldChanges(state = teacherState)
        CoroutineScope(Dispatchers.Default).launch {
            super.readAllDept()
        }
    }

}