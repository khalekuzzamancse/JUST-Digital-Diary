package schedule.presentationlogic.mapper// Import UI models and alias them to avoid conflict with domain models
import schedule.presentationlogic.model.ClassDetailModel as UIClassDetailModel
import schedule.presentationlogic.model.ClassScheduleModel as UIClassScheduleModel
import schedule.presentationlogic.model.DailyClassScheduleModel as UIDailyClassScheduleModel

import schedule.domain.model.ClassDetailModel
import schedule.domain.model.ClassScheduleModel
import schedule.domain.model.ClassModel as DomainDailyClassScheduleModel

class ClassScheduleModelMapper {
    fun uiToDomainModel(uiModel: UIClassScheduleModel): ClassScheduleModel {
        return ClassScheduleModel(
            session = uiModel.session,
            year = uiModel.year,
            semester = uiModel.semester,
            routine = uiModel.routine.map { fromDailyUIToDomainModel(it) }
        )
    }

    private fun fromDailyUIToDomainModel(uiModel: UIDailyClassScheduleModel): DomainDailyClassScheduleModel {
        return DomainDailyClassScheduleModel(
            day = uiModel.day,
            classes = uiModel.items.map { fromClassDetailUIToDomainModel(it) }
        )
    }

    private fun fromClassDetailUIToDomainModel(uiModel: UIClassDetailModel): ClassDetailModel {
        return ClassDetailModel(
            subject = uiModel.courseCode,
            time = uiModel.time,
            teacherName = uiModel.teacherName,
            durationInHours = uiModel.durationInHours
        )
    }
}
