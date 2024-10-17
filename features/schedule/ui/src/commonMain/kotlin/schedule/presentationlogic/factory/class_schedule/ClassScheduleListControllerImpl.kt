@file:Suppress("unused")
package schedule.presentationlogic.factory.class_schedule

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import schedule.domain.usecase.ReadClassScheduleUseCase
import schedule.presentationlogic.controller.ClassScheduleListController
import schedule.presentationlogic.controller.core.CoreController
import schedule.presentationlogic.ModelMapper
import schedule.presentationlogic.model.ClassScheduleModel

internal class ClassScheduleListControllerImpl(
    private val readAllUseCase: ReadClassScheduleUseCase
) : ClassScheduleListController, CoreController() {
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
                onFailure = { exception ->exception.showStatusMsg(optionalMsg = "Unable to load schedules")
                }
            )
        super.stopLoading()
    }

}