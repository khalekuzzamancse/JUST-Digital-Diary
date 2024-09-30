package schedule.ui.factory.exam_schedule

import schedule.ui.model.ExamDetailsModel
import schedule.ui.model.ExamScheduleModel

/**
 * - The controller delegates the responsibility of adding a new class to the schedule to this command
 *   As a result, the controller's responsibility is reduced, and its code remains concise
 *  - Additionally, implementing an undo operation in the future will be easier through this command
 */

interface AddCommand {
    fun execute(
        schedule: ExamScheduleModel,
        exam: ExamDetailsModel
    ): ExamScheduleModel
}
