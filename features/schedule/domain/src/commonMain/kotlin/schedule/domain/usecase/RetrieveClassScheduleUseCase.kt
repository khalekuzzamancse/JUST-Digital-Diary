package schedule.domain.usecase

import schedule.domain.model.ClassScheduleModel
import schedule.domain.repository.Repository

class RetrieveClassScheduleUseCase(
    private val repository: Repository
) {
    fun execute(deptId:String):Result<List<ClassScheduleModel>>{
        //check authentication then do operation
        return  repository.retrieveClassSchedule(deptId)
    }
}