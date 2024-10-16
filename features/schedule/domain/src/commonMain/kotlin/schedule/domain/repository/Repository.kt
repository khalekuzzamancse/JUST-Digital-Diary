package schedule.domain.repository

import schedule.domain.model.ClassScheduleModel
import schedule.domain.model.DepartmentModel
import schedule.domain.model.ExamScheduleModel

interface Repository {
    suspend  fun insert(model: ClassScheduleModel,deptId: String):Result<Unit>
    suspend fun readAllDept(): Result<List<DepartmentModel>>
    fun retrieveClassSchedule(deptId: String): Result<List<ClassScheduleModel>>
    fun retrieveExamSchedule(deptId: String): Result<List<ExamScheduleModel>>
}