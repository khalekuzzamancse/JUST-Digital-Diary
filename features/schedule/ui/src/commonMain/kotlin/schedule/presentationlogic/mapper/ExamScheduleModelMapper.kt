// Presenter to convert Domain models to UI models
package schedule.presentationlogic.mapper

import schedule.presentationlogic.model.ExamDetailsModel
import schedule.presentationlogic.model.ExamScheduleModel
import schedule.domain.model.ExamDetailsModel as DomainExamDetailsModel
import schedule.domain.model.ExamScheduleModel as DomainExamScheduleModel

class ExamScheduleModelMapper {

    // Convert Domain model (ExamScheduleModel) to UI model (ExamScheduleModel)
    fun fromDomainToUIModel(domainModel: DomainExamScheduleModel): ExamScheduleModel {
        return ExamScheduleModel(
            dept = domainModel.dept,
            session = domainModel.session,
            year = domainModel.year,
            semester = domainModel.semester,
            exams = domainModel.exams.map { fromExamDetailsDomainToUIModel(it) } // Convert each exam detail
        )
    }

    // Convert Domain model (ExamDetailsModel) to UI model (ExamDetailsModel)
    private fun fromExamDetailsDomainToUIModel(domainModel: DomainExamDetailsModel): ExamDetailsModel {
        return ExamDetailsModel(
            courseCode = domainModel.courseCode,
            courseTitle = domainModel.courseTitle,
            time = domainModel.time,
            date = domainModel.date
        )
    }
}
