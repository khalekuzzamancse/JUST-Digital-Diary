package schedule.di

import schedule.data.factory.DataModuleFactory
import schedule.domain.usecase.RetrieveClassScheduleUseCase
import schedule.domain.usecase.RetrieveExamScheduleUseCase

object DomainModuleFactory {
    fun createRetrieveClassScheduleUseCase()=
        RetrieveClassScheduleUseCase(DataModuleFactory.createRepository())
    fun createRetrieveExaScheduleUseCase()=
        RetrieveExamScheduleUseCase(DataModuleFactory.createRepository())
}