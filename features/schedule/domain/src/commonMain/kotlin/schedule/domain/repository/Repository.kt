package schedule.domain.repository

import schedule.domain.model.ClassScheduleModel
import schedule.domain.model.ExamScheduleModel

interface Repository {
    fun retrieveClassSchedule(deptId: String): Result<List<ClassScheduleModel>>
    fun retrieveExamSchedule(deptId: String): Result<List<ExamScheduleModel>>
}