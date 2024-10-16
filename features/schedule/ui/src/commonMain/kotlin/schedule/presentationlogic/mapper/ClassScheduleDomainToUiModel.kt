package schedule.presentationlogic.mapper

import schedule.domain.model.ClassDetailModel as DomainClassDetailModel
import schedule.domain.model.ClassScheduleModel as DomainClassScheduleModel
import schedule.domain.model.ClassModel as DomainDailyClassScheduleModel

import schedule.presentationlogic.model.ClassDetailModel
import schedule.presentationlogic.model.ClassScheduleModel
import schedule.presentationlogic.model.DailyClassScheduleModel

class ClassScheduleDomainToUiModel {

    fun covert(domainModel: DomainClassScheduleModel): ClassScheduleModel {
        return ClassScheduleModel(
            deptName = "TODO",
            session = domainModel.session,
            year = domainModel.year,
            semester = domainModel.semester,
            routine = domainModel.routine.map { fromDailyDomainToUIModel(it) }
        )
    }

    private fun fromDailyDomainToUIModel(domainModel: DomainDailyClassScheduleModel): DailyClassScheduleModel {
        return DailyClassScheduleModel(
            day = domainModel.day,
            items = domainModel.classes.map { fromClassDetailDomainToUIModel(it) }
        )
    }


    private fun fromClassDetailDomainToUIModel(domainModel: DomainClassDetailModel): ClassDetailModel {
        return ClassDetailModel(
            courseCode = domainModel.subject,
            time = domainModel.time,
            teacherName = domainModel.teacherName,
            durationInHours = domainModel.durationInHours
        )
    }


}
