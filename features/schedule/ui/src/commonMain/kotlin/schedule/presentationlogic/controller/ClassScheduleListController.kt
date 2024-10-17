package schedule.presentationlogic.controller

import kotlinx.coroutines.flow.StateFlow
import schedule.presentationlogic.controller.core.ICoreController
import schedule.presentationlogic.model.ClassScheduleModel

interface ClassScheduleListController:ICoreController {
    val schedules:StateFlow<List<ClassScheduleModel>>

}