@file:Suppress("unused")
package schedule.domain.usecase

import schedule.domain.model.ClassScheduleReadModel
import schedule.domain.model.ClassScheduleWriteModel
import schedule.domain.repository.Repository

class InsertCalenderUseCase(
    private val repository: Repository
) {
    suspend fun execute(model: ClassScheduleWriteModel, deptId: String) =
        repository.insert(model, deptId)
}