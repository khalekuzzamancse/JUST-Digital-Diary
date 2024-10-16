@file:Suppress("unused")
package schedule.presentationlogic.factory

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import schedule.domain.exception.CustomException
import schedule.domain.usecase.RetrieveClassScheduleUseCase
import schedule.presentationlogic.controller.ClassScheduleViewerController
import schedule.presentationlogic.controller.core.CoreControllerImpl
import schedule.presentationlogic.mapper.ModelMapper
import schedule.presentationlogic.model.ClassScheduleModel

internal class ClassScheduleViewerControllerImpl(
    private val readAllUseCase: RetrieveClassScheduleUseCase
) : ClassScheduleViewerController, CoreControllerImpl() {
    private val _schedule = MutableStateFlow(emptyList<ClassScheduleModel>())


    override val schedules = _schedule.asStateFlow()
    override val isLoading = super._isLoading
    override val statusMessage = super._statusMessage
    init {
        CoroutineScope(Dispatchers.Default).launch {
            readAllSchedule()
        }
    }

    private suspend fun readAllSchedule() {
        super.startLoading()
        readAllUseCase.execute()
            .fold(
                onSuccess = { models ->
                    with(ModelMapper) {
                        _schedule.update { models.map { it.toModel() } }
                    }
                },
                onFailure = { exception ->
                    when (exception) {
                        is CustomException -> super.updateErrorMessage(exception.message)
                        else -> super.updateErrorMessage("Failed Load")
                    }
                }
            )
        super.stopLoading()
    }

}