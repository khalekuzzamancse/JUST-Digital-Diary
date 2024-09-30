package schedule.ui.factory.class_schedule

import schedule.ui.model.ClassDetailModel
import schedule.ui.model.ClassScheduleModel

/**
 * - The controller delegates the responsibility of adding a new class to the schedule to this command
 *   As a result, the controller's responsibility is reduced, and its code remains concise
 *  - Additionally, implementing an undo operation in the future will be easier through this command
 */

interface AddCommand {
    fun execute(
        scheduleModel: ClassScheduleModel,
        day: String,
        classDetail: ClassDetailModel
    ): ClassScheduleModel
}