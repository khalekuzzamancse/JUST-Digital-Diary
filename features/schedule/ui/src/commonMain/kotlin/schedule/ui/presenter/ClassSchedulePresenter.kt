// Presenter to convert Domain models to UI models
package schedule.ui.presenter

// Import domain models and alias them to avoid conflict with UI models
import schedule.domain.model.ClassDetailModel as DomainClassDetailModel
import schedule.domain.model.ClassScheduleModel as DomainClassScheduleModel
import schedule.domain.model.DailyClassScheduleModel as DomainDailyClassScheduleModel

import schedule.ui.model.ClassDetailModel
import schedule.ui.model.ClassScheduleModel
import schedule.ui.model.DailyClassScheduleModel

class ClassSchedulePresenter {

    // Convert DomainClassScheduleModel (Domain) to ClassScheduleModel (UI)
    fun fromDomainToUIModel(domainModel: DomainClassScheduleModel): ClassScheduleModel {
        return ClassScheduleModel(
            dept = domainModel.dept,
            session = domainModel.session,
            year = domainModel.year,
            semester = domainModel.semester,
            routine = domainModel.routine.map { fromDailyDomainToUIModel(it) } // Convert daily schedule
        )
    }

    // Convert DomainDailyClassScheduleModel (Domain) to DailyClassScheduleModel (UI)
    private fun fromDailyDomainToUIModel(domainModel: DomainDailyClassScheduleModel): DailyClassScheduleModel {
        return DailyClassScheduleModel(
            day = domainModel.day,
            items = domainModel.items.map { fromClassDetailDomainToUIModel(it) } // Convert class details
        )
    }

    // Convert DomainClassDetailModel (Domain) to ClassDetailModel (UI)
    private fun fromClassDetailDomainToUIModel(domainModel: DomainClassDetailModel): ClassDetailModel {
        return ClassDetailModel(
            courseCode = domainModel.subject,
            time = formatTime(domainModel.time), // Format time if needed
            teacherShortName = domainModel.room,
            durationInHours = domainModel.durationInHours
        )
    }

    // Helper method to format the time (could be simple or complex depending on needs)
    private fun formatTime(time: String): String {
        // In this example, we keep the time as is. You can modify this to suit your needs.
        return time
    }

    // Helper method to convert duration from hours to a readable string
    private fun formatDuration(durationInHours: Int): String {
        return if (durationInHours == 1) {
            "$durationInHours hour"
        } else {
            "$durationInHours hours"
        }
    }
}
