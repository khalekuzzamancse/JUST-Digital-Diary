@file:Suppress("functionName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.DeptEntryController
import academic.presentationlogic.controller.admin.InsertDeptController
import academic.presentationlogic.ModelMapper
import faculty.domain.usecase.admin.InsertDepartmentUseCase
import faculty.domain.usecase.public_.ReadAllFactualityUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class InsertDeptControllerImpl(
    private val writeUseCase: InsertDepartmentUseCase,
    readUseCase: ReadAllFactualityUseCase,
    validator  : DeptEntryController.Validator
) : DeptEntryControllerImpl(readUseCase = readUseCase, validator =validator ), InsertDeptController {

    init {
        CoroutineScope(Dispatchers.Default).launch {
            super.readFaculties()
        }
        super.validator.observeFieldChanges(super._dept)
    }

    override suspend fun insert() {
        try {
            super.startLoading()
            writeUseCase
                .execute(with(ModelMapper) { _dept.value.toDomainModelOrThrow() })
                .showStatusMsg(operation = "Insertion")

            super.stopLoading()
        }
        catch (e:Exception){
            e.showStatusMsg(optionalMsg = "Priority must be an integer")
        }

    }


}