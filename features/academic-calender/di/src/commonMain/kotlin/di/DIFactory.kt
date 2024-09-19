@file:Suppress("UnUsed")

package di

import data.factory.DataModuleFactory
import domain.usecase.AddCalendarUseCase
import domain.usecase.RetrieveCalendarUseCase

object DIFactory {
    fun createRetrieveCalenderUseCase(): RetrieveCalendarUseCase =
        RetrieveCalendarUseCase(
            repository = DataModuleFactory.createRepository(),
            validationService = DataModuleFactory.createCalenderService()
        )

    fun createAddCalenderUseCase(): AddCalendarUseCase =
        AddCalendarUseCase(
            repository = DataModuleFactory.createRepository(),
            service = DataModuleFactory.createUserService()
        )
}