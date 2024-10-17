@file:Suppress("unused")
package schedule.di

import schedule.data.factory.DataFactory
import schedule.domain.usecase.InsertCalenderUseCase
import schedule.domain.usecase.ReadAllDeptUseCase
import schedule.domain.usecase.ReadClassScheduleUseCase
import schedule.domain.usecase.ReadExamScheduleUseCase

object DiContainer {
    fun readClassScheduleUseCase()=
        ReadClassScheduleUseCase(DataFactory.repository())
    fun readExaScheduleUseCase()=
        ReadExamScheduleUseCase(DataFactory.repository())
    fun readAllDeptUseCase():ReadAllDeptUseCase=ReadAllDeptUseCase(DataFactory.repository())
    fun insertUseCase():InsertCalenderUseCase=InsertCalenderUseCase(DataFactory.repository())
}