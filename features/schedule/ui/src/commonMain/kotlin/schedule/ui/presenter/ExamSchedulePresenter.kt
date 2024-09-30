// Presenter to convert Domain models to UI models
package schedule.ui.presenter

import schedule.ui.model.ExamDetailsModel
import schedule.ui.model.ExamScheduleModel
import schedule.domain.model.ExamDetailsModel as DomainExamDetailsModel
import schedule.domain.model.ExamScheduleModel as DomainExamScheduleModel

class ExamSchedulePresenter {

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
