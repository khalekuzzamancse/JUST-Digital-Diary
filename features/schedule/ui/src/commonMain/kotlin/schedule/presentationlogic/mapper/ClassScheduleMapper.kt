package schedule.presentationlogic.mapper

import schedule.presentationlogic.model.ClassDetailModel as UIDetailModel
import schedule.presentationlogic.model.ClassScheduleModel as UIScheduleModel
import schedule.presentationlogic.model.DailyClassScheduleModel as UIDailyModel

import schedule.domain.model.ClassDetailModel as DomainDetailModel
import schedule.domain.model.ClassScheduleModel as DomainScheduleModel
import schedule.domain.model.ClassModel as DomainDailyModel

object ClassScheduleMapper {

    class DomainToUi {

        fun convert(domainModel: DomainScheduleModel): UIScheduleModel {
            return UIScheduleModel(
                deptName = "TODO",
                session = domainModel.session,
                year = domainModel.year,
                semester = domainModel.semester,
                routine = domainModel.routine.map { fromDailyDomainToUIModel(it) }
            )
        }

        private fun fromDailyDomainToUIModel(domainModel: DomainDailyModel): UIDailyModel {
            return UIDailyModel(
                day = domainModel.day,
                items = domainModel.classes.map { fromClassDetailDomainToUIModel(it) }
            )
        }

        private fun fromClassDetailDomainToUIModel(domainModel: DomainDetailModel): UIDetailModel {
            return UIDetailModel(
                courseCode = domainModel.subject,
                time = domainModel.time,
                teacherName = domainModel.teacherName,
                durationInHours = domainModel.durationInHours
            )
        }
    }

    class UiToDomain {

        fun convert(uiModel: UIScheduleModel): DomainScheduleModel {
            return DomainScheduleModel(
                session = uiModel.session,
                year = uiModel.year,
                semester = uiModel.semester,
                routine = uiModel.routine.map { fromDailyUIToDomainModel(it) }
            )
        }

        private fun fromDailyUIToDomainModel(uiModel: UIDailyModel): DomainDailyModel {
            return DomainDailyModel(
                day = uiModel.day,
                classes = uiModel.items.map { fromClassDetailUIToDomainModel(it) }
            )
        }

        private fun fromClassDetailUIToDomainModel(uiModel: UIDetailModel): DomainDetailModel {
            return DomainDetailModel(
                subject = uiModel.courseCode,
                time = uiModel.time,
                teacherName = uiModel.teacherName,
                durationInHours = uiModel.durationInHours
            )
        }
    }
}
