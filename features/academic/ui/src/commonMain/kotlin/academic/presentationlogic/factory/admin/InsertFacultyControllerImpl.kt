@file:Suppress("functionName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.FacultyEntryController
import academic.presentationlogic.controller.admin.InsertFacultyController
import academic.presentationlogic.mapper.AdminModelMapper
import common.ui.SnackBarMessage
import core.customexception.CustomException
import faculty.domain.usecase.admin.InsertFacultyUseCase

/**
 * - Using explicit `super` to understand the code base easily, specifically understanding the delegation
 */
internal class InsertFacultyControllerImpl(
    private val useCase: InsertFacultyUseCase,
    validator: FacultyEntryController.Validator
) : FacultyEntryControllerImpl(validator), InsertFacultyController {

    init {
        super.validator.activate(super.faculty)
    }

    override suspend fun insert() {
        val model = with(AdminModelMapper) { faculty.value.toDomainModelOrThrow() }
        super.startLoading()
        useCase.execute(model)
            .updateStatusMsg(operationName = "Insertion")

        super.stopLoading()

    }

}