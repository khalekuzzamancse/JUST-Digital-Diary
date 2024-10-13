@file:Suppress("functionName")

package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.FacultyAdminBaseController
import academic.presentationlogic.controller.admin.InsertFacultyController
import academic.presentationlogic.mapper.ModelMapper
import faculty.domain.exception.CustomException
import faculty.domain.usecase.admin.AddFacultyUseCase

internal class InsertFacultyControllerImpl(
    private val useCase: AddFacultyUseCase,
    validator: FacultyAdminBaseController.Validator
) : FacultyAdminBaseControllerImpl(validator), InsertFacultyController {

    init {
        super.validator.activate(super.faculty)
    }

    override suspend fun onAddRequest() {
        val model = with(ModelMapper) { faculty.value.toDomainModelOrThrow() }
        super.onNetworkIOStart()
        val result = useCase.execute(model)
        result.fold(
            onSuccess = {
                updateErrorMessage("Added Successfully")

            },
            onFailure = { exception ->
                when (exception) {
                    is CustomException -> {
                        updateErrorMessage(exception.message)
                    }

                    else -> {
                        updateErrorMessage("Something went wrong")
                    }

                }
            }
        )
        super.onNetworkIOStop()

    }
}