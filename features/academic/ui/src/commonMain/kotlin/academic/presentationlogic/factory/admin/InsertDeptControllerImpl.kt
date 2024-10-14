@file:Suppress("functionName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.DeptEntryController
import academic.presentationlogic.controller.admin.InsertDeptController
import academic.presentationlogic.mapper.ModelMapper
import faculty.domain.exception.CustomException
import faculty.domain.usecase.admin.InsertDepartmentUseCase
import faculty.domain.usecase.public_.RetrieveFactualityUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class InsertDeptControllerImpl(
    private val writeUseCase: InsertDepartmentUseCase,
    readUseCase: RetrieveFactualityUseCase,
    validator  : DeptEntryController.Validator
) : DeptEntryControllerImpl(readUseCase = readUseCase, validator =validator ), InsertDeptController {

    init {
        CoroutineScope(Dispatchers.Default).launch {
            super.retrieveFaculties()
        }
    }

    override suspend fun insert() {
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


}