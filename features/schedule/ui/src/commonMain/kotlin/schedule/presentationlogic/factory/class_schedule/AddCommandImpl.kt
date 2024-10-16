package schedule.presentationlogic.factory.class_schedule

import schedule.presentationlogic.model.ClassDetailModel
import schedule.presentationlogic.model.ClassScheduleModel
import schedule.presentationlogic.model.DailyClassScheduleModel

class AddCommandImpl : AddCommand {

    override fun execute(
        scheduleModel: ClassScheduleModel,
        day: String,
        classDetail: ClassDetailModel
    ): ClassScheduleModel {
        return if (scheduleModel.isDayExisting(day)) {
            scheduleModel.updateExistingDay(day,classDetail)
        } else {
            scheduleModel.addNewDay(day, classDetail)
        }
    }

    // Check if the day exists in the current routine
    private fun ClassScheduleModel.isDayExisting(day: String): Boolean {
        return routine.any { it.day == day }
    }

    // Update the schedule for an existing day
    private fun ClassScheduleModel.updateExistingDay(day: String, classDetail: ClassDetailModel): ClassScheduleModel {
        val updatedRoutine = this.routine.map { dailyClassSchedule ->
            if (dailyClassSchedule.day == day) {
                dailyClassSchedule.copy(items = addClassToDay(dailyClassSchedule.items, classDetail))
            } else {
                dailyClassSchedule
            }
        }
        return this.copy(routine = updatedRoutine)
    }

    // Add a new day with the class detail if the day doesn't exist
    private fun ClassScheduleModel.addNewDay(day: String, classDetail: ClassDetailModel): ClassScheduleModel {
        val newDailySchedule = DailyClassScheduleModel(day, mutableListOf(classDetail))
        val updatedRoutine = this.routine.toMutableList().apply {
            add(newDailySchedule)
        }
        return this.copy(routine = updatedRoutine)
    }

    // Helper method to add the new class detail to an existing day's items
    private fun addClassToDay(existingItems: List<ClassDetailModel>, classDetail: ClassDetailModel): List<ClassDetailModel> {
        val updatedItems = existingItems.toMutableList()
        updatedItems.add(classDetail)
        return updatedItems
    }
}
