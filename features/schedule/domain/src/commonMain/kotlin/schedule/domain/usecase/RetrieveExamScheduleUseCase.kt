package schedule.domain.usecase

import schedule.domain.model.ExamScheduleModel
import schedule.domain.repository.Repository

class RetrieveExamScheduleUseCase(
    private val repository: Repository
) {

    fun execute(deptId:String):Result<List<ExamScheduleModel>>{
        //check authentication then do operation
        return  repository.retrieveExamSchedule(deptId)
    }
}