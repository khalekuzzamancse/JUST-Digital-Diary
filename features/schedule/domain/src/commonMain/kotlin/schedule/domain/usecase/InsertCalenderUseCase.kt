@file:Suppress("unused")
package schedule.domain.usecase

import schedule.domain.model.ClassScheduleModel
import schedule.domain.repository.Repository

class InsertCalenderUseCase(
    private val repository: Repository
) {
    suspend fun execute(model: ClassScheduleModel, deptId: String) =
        repository.insert(model, deptId)
}