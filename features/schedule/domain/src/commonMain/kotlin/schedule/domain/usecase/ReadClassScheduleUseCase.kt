package schedule.domain.usecase

import schedule.domain.model.ClassScheduleReadModel
import schedule.domain.repository.Repository

class ReadClassScheduleUseCase(
    private val repository: Repository
) {
    suspend fun execute():Result<List<ClassScheduleReadModel>>{
        //check authentication then do operation
        return  repository.retrieveClassSchedule()
    }
}