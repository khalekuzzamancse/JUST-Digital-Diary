@file:Suppress("unused")

package di

import data.factory.DataModuleFactory
import feature.academiccalender.domain.usecase.ReadRawCalenderUseCase
import feature.academiccalender.domain.usecase.InsertUseCase
import feature.academiccalender.domain.usecase.ReadAcademicCalenderUseCase
import feature.academiccalender.domain.usecase.UpdateUseCase

object DiContainer {
    fun readAcademicCalender(): ReadAcademicCalenderUseCase =
        ReadAcademicCalenderUseCase(
            repository = DataModuleFactory.repository(),
        )

    fun readRawCalenderUseCase(): ReadRawCalenderUseCase =
        ReadRawCalenderUseCase(
            repository = DataModuleFactory.repository()
        )

    fun insertUseCase(): InsertUseCase =
        InsertUseCase(
            repository = DataModuleFactory.repository(),
        )

    fun updateUseCase(): UpdateUseCase =
        UpdateUseCase(repository = DataModuleFactory.repository())
}