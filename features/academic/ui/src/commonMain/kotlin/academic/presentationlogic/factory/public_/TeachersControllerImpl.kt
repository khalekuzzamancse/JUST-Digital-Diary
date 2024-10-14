@file:Suppress("unused", "FunctionName")

package academic.presentationlogic.factory.public_

import academic.presentationlogic.controller.core.CoreControllerImpl
import academic.presentationlogic.model.public_.TeacherModel
import academic.presentationlogic.controller.public_.TeachersController
import academic.presentationlogic.mapper.PublicModelMapper
import faculty.domain.exception.CustomException
import faculty.domain.usecase.public_.RetrieveTeachersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class TeachersControllerImpl(
    private val useCase: RetrieveTeachersUseCase,
) : TeachersController, CoreControllerImpl() {

    private val _teachers = MutableStateFlow<List<TeacherModel>>(emptyList())

    override val isLoading = super._isLoading.asStateFlow()
    override val statusMessage = super._statusMessage.asStateFlow()

    override val teachers = _teachers.asStateFlow()

    override suspend fun fetch(deptId: String) {
        super.startLoading()
        val result = useCase.execute(deptId)
        result.fold(
            onSuccess = { models ->
                _teachers.update {
                    models
                        .map { PublicModelMapper.toTeacherUiModel(it) }
                }
            },
            onFailure = { exception ->

                when (exception) {
                    is CustomException -> super.updateErrorMessage(exception.message)
                    else -> super.updateErrorMessage("Something went wrong")
                }
            }
        )
        super.stopLoading()
    }
}