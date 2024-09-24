package schedule.ui.factory.exam_schedule

import schedule.ui.model.ExamDetailsModel
import schedule.ui.model.ExamScheduleModel

class AddCommandImpl:AddCommand {
    override fun execute(
        schedule: ExamScheduleModel,
        exam: ExamDetailsModel
    ): ExamScheduleModel {
        val existingExam=schedule.exams
        return  schedule.copy(
            exams = existingExam+exam
        )
    }
}