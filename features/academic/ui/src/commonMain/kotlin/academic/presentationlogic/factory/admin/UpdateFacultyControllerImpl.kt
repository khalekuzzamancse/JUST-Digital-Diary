@file:Suppress("functionName","propertyName")
package academic.presentationlogic.factory.admin

import academic.presentationlogic.controller.admin.FacultyEntryController
import academic.presentationlogic.controller.admin.UpdateFacultyController
import academic.presentationlogic.mapper.ModelMapper
import faculty.domain.exception.CustomException
import faculty.domain.usecase.admin.GetFacultyByIdUseCase
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
    private val readUseCase: GetFacultyByIdUseCase,
    validator: FacultyEntryController.Validator
) : FacultyEntryControllerImpl(validator), UpdateFacultyController {

    init {
        super.validator.activate(faculty)
        CoroutineScope(Dispatchers.Default).launch {
            _readFaculty()
        }
    }

    override suspend fun update() {
        super.onNetworkIOStart()
        val model = with(ModelMapper) { faculty.value.toDomainModelOrThrow() }

        val result = writeUseCase.execute(model)
        result.fold(
            onSuccess = {
                super.updateErrorMessage("Added Successfully")
            },
            onFailure = { exception ->
                when (exception) {
                    is CustomException -> super.updateErrorMessage(exception.message)
                    else -> super.updateErrorMessage("Something went wrong")
                }
            }
        )
        super.onNetworkIOStop()
    }

    private suspend fun _readFaculty() {
        super.onNetworkIOStart()
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
                when (exception) {
                    is CustomException -> super.updateErrorMessage(exception.message)
                    else -> super.updateErrorMessage("Something went wrong!,Faculty not found")
                }
            }
        )
        super.onNetworkIOStop()
    }

}