@file:Suppress("UnUsed")

package di

import data.factory.DataModuleFactory
import domain.usecase.AddCalendarUseCase
import domain.usecase.GetRawCalenderUseCase
import domain.usecase.RetrieveCalendarUseCase

object DIFactory {
    fun createAcademicRetrieveCalenderUseCase(): RetrieveCalendarUseCase =
        RetrieveCalendarUseCase(
            repository = DataModuleFactory.createRepository(),
            validationService = DataModuleFactory.createCalenderService()
        )

    fun createRawRetrieveCalenderUseCase(): GetRawCalenderUseCase =
        GetRawCalenderUseCase(
            repository = DataModuleFactory.createRepository()
        )

    fun createAddCalenderUseCase(): AddCalendarUseCase =
        AddCalendarUseCase(
            repository = DataModuleFactory.createRepository(),
            service = DataModuleFactory.createUserService()
        )
}