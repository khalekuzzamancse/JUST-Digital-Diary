@file:Suppress("UnUsed")

package di

import data.factory.DataModuleFactory
import domain.usecase.GetCalenderByYearUseCase
import domain.usecase.ReadAcademicCalender

object DIFactory {
    fun createAcademicRetrieveCalenderUseCase(): ReadAcademicCalender =
        ReadAcademicCalender(
            repository = DataModuleFactory.createRepository(),
            validationService = DataModuleFactory.createCalenderService()
        )

    fun createRawRetrieveCalenderUseCase(): GetCalenderByYearUseCase =
        GetCalenderByYearUseCase(
            repository = DataModuleFactory.createRepository()
        )

    fun createAddCalenderUseCase(): InsertAcademicCalendarUseCase =
        InsertAcademicCalendarUseCase(
            repository = DataModuleFactory.createRepository(),
            service = DataModuleFactory.createUserService()
        )
}