@file:Suppress("unused")
package schedule.di

import schedule.data.factory.DataModuleFactory
import schedule.domain.usecase.InsertCalenderUseCase
import schedule.domain.usecase.ReadAllDeptUseCase
import schedule.domain.usecase.RetrieveClassScheduleUseCase
import schedule.domain.usecase.RetrieveExamScheduleUseCase

object DiFactory {
    fun createRetrieveClassScheduleUseCase()=
        RetrieveClassScheduleUseCase(DataModuleFactory.createRepository())
    fun createRetrieveExaScheduleUseCase()=
        RetrieveExamScheduleUseCase(DataModuleFactory.createRepository())
    fun readAllDeptUseCase():ReadAllDeptUseCase=ReadAllDeptUseCase(DataModuleFactory.createRepository())
    fun insertUseCase():InsertCalenderUseCase=InsertCalenderUseCase(DataModuleFactory.createRepository())
}