package schedule.presentationlogic.factory.exam_schedule

import schedule.presentationlogic.model.ExamDetailsModel
import schedule.presentationlogic.model.ExamScheduleModel

class AddCommandImpl: AddCommand {
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