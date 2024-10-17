@file:Suppress("unused", "FunctionName")

package academic.presentationlogic.factory.public_

import academic.presentationlogic.controller.core.CoreControllerImpl
import academic.presentationlogic.controller.public_.TeachersController
import academic.presentationlogic.mapper.ReadModelMapper
import academic.presentationlogic.model.TeacherReadModel
import common.ui.SnackBarMessage
import core.customexception.CustomException
import faculty.domain.usecase.public_.ReadTeachersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class TeachersControllerImpl(
    private val useCase: ReadTeachersUseCase,
) : TeachersController, CoreControllerImpl() {

    private val _teachers = MutableStateFlow<List<TeacherReadModel>>(emptyList())

    override val isLoading = super._isLoading.asStateFlow()
    override val statusMessage = super._statusMessage.asStateFlow()

    override val teachers = _teachers.asStateFlow()

    override suspend fun fetch(deptId: String) {
        super.startLoading()
        val result = useCase.execute(deptId)
        result.fold(
            onSuccess = { models ->
                _teachers.update {
                    with(ReadModelMapper) {
                        models.map { it.toModel() }
                    }
                }
            },
            onFailure = { exception -> exception.updateStatusMessage() }
        )
        super.stopLoading()
    }
}