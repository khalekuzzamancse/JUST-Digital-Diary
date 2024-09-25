package auth.di

import auth.data.factory.DataModuleFactory
import auth.domain.usecase.LoginUseCase


/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object DiContainer {
    fun createLoginUseCase(): LoginUseCase = LoginUseCase(
        repository = DataModuleFactory.createLoginRepository()
    )

}