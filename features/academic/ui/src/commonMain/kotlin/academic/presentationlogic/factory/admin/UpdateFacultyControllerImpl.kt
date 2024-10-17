@file:Suppress("functionName","propertyName")
package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.FacultyEntryController
import academic.presentationlogic.controller.admin.UpdateFacultyController
import academic.presentationlogic.mapper.AdminModelMapper
import common.ui.SnackBarMessage
import core.customexception.CustomException
import faculty.domain.usecase.admin.ReadFacultyUseCase
import faculty.domain.usecase.admin.UpdateFacultyUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * - Using explicit `super` to understand the code base easily, specifically understanding the delegation
 */
internal class UpdateFacultyControllerImpl(
    private val facultyId: String,
    private val writeUseCase: UpdateFacultyUseCase,
    private val readUseCase: ReadFacultyUseCase,
    validator: FacultyEntryController.Validator
) : FacultyEntryControllerImpl(validator), UpdateFacultyController {

    init {
        super.validator.activate(faculty)
        CoroutineScope(Dispatchers.Default).launch {
            _readFaculty()
        }
    }

    override suspend fun update() {
        super.startLoading()
        val model = with(AdminModelMapper) { faculty.value.toDomainModelOrThrow() }

        val result = writeUseCase.execute(facultyId,model)
        result.updateStatusMsg(operationName = "Update")
        super.stopLoading()
    }

    private suspend fun _readFaculty() {
        super.startLoading()
        val result = readUseCase.execute(facultyId)
        result.fold(
            onSuccess = { model ->
                super._faculty.update { state ->
                    state.copy(
                        name = model.name,
                        priority = model.priority.toString()
                    )
                }
            },
            onFailure = { exception ->
                exception.updateStatusMessage(optionalMsg = "Failed to fetch")
            }
        )
        super.stopLoading()
    }

}