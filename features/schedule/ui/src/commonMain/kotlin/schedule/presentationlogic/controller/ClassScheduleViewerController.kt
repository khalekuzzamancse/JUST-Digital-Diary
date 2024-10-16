package schedule.presentationlogic.controller

import kotlinx.coroutines.flow.StateFlow
import schedule.presentationlogic.controller.core.CoreController
import schedule.presentationlogic.model.ClassScheduleModel

interface ClassScheduleViewerController:CoreController {
    val schedules:StateFlow<List<ClassScheduleModel>>

}