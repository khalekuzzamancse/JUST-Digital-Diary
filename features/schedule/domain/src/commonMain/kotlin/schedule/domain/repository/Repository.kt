package schedule.domain.repository

import schedule.domain.model.ClassScheduleReadModel
import schedule.domain.model.ClassScheduleWriteModel
import schedule.domain.model.DepartmentModel
import schedule.domain.model.ExamScheduleModel

interface Repository {
    suspend  fun insert(model: ClassScheduleWriteModel, deptId: String):Result<Unit>
    suspend fun readAllDept(): Result<List<DepartmentModel>>
    suspend fun retrieveClassSchedule(): Result<List<ClassScheduleReadModel>>
    fun retrieveExamSchedule(deptId: String): Result<List<ExamScheduleModel>>
}