@file:Suppress("UnUsed")

package di

import data.factory.DataModuleFactory
import feature.academiccalender.domain.usecase.ReadRawCalenderUseCase
import feature.academiccalender.domain.usecase.InsertUseCase
import feature.academiccalender.domain.usecase.ReadAcademicCalenderUseCase
import feature.academiccalender.domain.usecase.UpdateUseCase

object DIFactory {
    fun readAcademicCalender(): ReadAcademicCalenderUseCase =
        ReadAcademicCalenderUseCase(
            repository = DataModuleFactory.createRepository(),
        )

    fun readRawCalenderUseCase(): ReadRawCalenderUseCase =
        ReadRawCalenderUseCase(
            repository = DataModuleFactory.createRepository()
        )

    fun insertUseCase(): InsertUseCase =
        InsertUseCase(
            repository = DataModuleFactory.createRepository(),
        )

    fun updateUseCase(): UpdateUseCase =
        UpdateUseCase(repository = DataModuleFactory.createRepository())
}