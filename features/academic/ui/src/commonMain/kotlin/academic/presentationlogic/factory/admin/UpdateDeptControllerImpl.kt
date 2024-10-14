@file:Suppress("functionName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.DeptEntryController
import academic.presentationlogic.controller.admin.UpdateDeptController
import academic.presentationlogic.mapper.ModelMapper
import faculty.domain.exception.CustomException
import faculty.domain.usecase.admin.ReadDeptUseCase
import faculty.domain.usecase.admin.UpdateDepartmentUseCase
import faculty.domain.usecase.public_.RetrieveFactualityUseCase
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
    readFacultyUseCase: RetrieveFactualityUseCase,
    validator: DeptEntryController.Validator
) : DeptEntryControllerImpl(readUseCase = readFacultyUseCase, validator = validator),
    UpdateDeptController {

    init {
        CoroutineScope(Dispatchers.Default).launch {
            super.retrieveFaculties()
        }
        CoroutineScope(Dispatchers.Default).launch {
            _readDept()
        }
    }

    override suspend fun update() {
        super.onNetworkIOStart()
        writeUseCase
            .execute(with(ModelMapper) { _dept.value.toDomainModelOrThrow() })
            .fold(
                onSuccess = {
                    super.updateErrorMessage("Added Successfully")
                },
                onFailure = { exception ->
                    when (exception) {
                        is CustomException -> super.updateErrorMessage(exception.message)
                        else ->
                            super.updateErrorMessage("Failed to load faculties")
                    }
                }
            )
        super.onNetworkIOStop()
    }

    private suspend fun _readDept() {
        super.onNetworkIOStart()
        readDeptUseCase
            .execute(deptId)
            .fold(
                onSuccess = { model ->
                    super._dept.update { state ->
                        state.copy(
                            name = model.name,
                            shortName = model.shortname,
                            priority = model.priority.toString()
                        )
                    }
                },
                onFailure = { exception ->
                    when (exception) {
                        is CustomException -> super.updateErrorMessage(exception.message)
                        else -> super.updateErrorMessage("Failed to load dept")
                    }
                }
            )
        super.onNetworkIOStop()
    }


}