@file:Suppress("functionName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.DeptEntryController
import academic.presentationlogic.controller.admin.UpdateDeptController
import academic.presentationlogic.ModelMapper
import faculty.domain.usecase.admin.ReadDeptUseCase
import faculty.domain.usecase.admin.UpdateDepartmentUseCase
import faculty.domain.usecase.public_.ReadAllFactualityUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * - Using explicit `super` to understand the code base easily, specifically understanding the delegation
 */
internal class UpdateDeptControllerImpl(
    private val deptId: String,
    private val readDeptUseCase: ReadDeptUseCase,
    private val writeUseCase: UpdateDepartmentUseCase,
    readFacultyUseCase: ReadAllFactualityUseCase,
    validator: DeptEntryController.Validator
) : DeptEntryControllerImpl(readUseCase = readFacultyUseCase, validator = validator),
    UpdateDeptController {

    init {
        CoroutineScope(Dispatchers.Default).launch {
            super.readFaculties()
        }
        CoroutineScope(Dispatchers.Default).launch {
            _readDept()
        }


        super.validator.observeFieldChanges(super._dept)

    }

    override suspend fun update() {
        super.startLoading()
        writeUseCase
            .execute(deptId, with(ModelMapper) { _dept.value.toDomainModelOrThrow() })
            .showStatusMsg(operation = "Update")
        super.stopLoading()
    }

    private suspend fun _readDept() {
        super.startLoading()
        readDeptUseCase
            .execute(deptId)
            .fold(
                onSuccess = { model ->
                    super._dept.update { state ->
                        state.copy(
                            name = model.name,
                            shortname = model.shortname,
                            priority = model.priority.toString()
                        )
                    }
                    try {
                        //TODO: Have a problem to data layer, faculty id is not loaded
                        // TODO: Refactor later, Edge case: faculty may not be leaded yet...
                        super.onFacultySelected(
                            super.faculties.value
                                .map { it.id }
                                .indexOf(model.facultyId)
                        )
                    } catch (_: Exception) {

                    }


                },
                onFailure = { exception -> exception.showStatusMsg(optionalMsg = "Unable to load departments") }
            )
        super.stopLoading()
    }


}