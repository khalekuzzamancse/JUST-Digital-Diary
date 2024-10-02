package auth.di

import auth.data.factory.DataModuleFactory
import auth.domain.usecase.AccountVerifyUseCase
import auth.domain.usecase.LoginUseCase
import auth.domain.usecase.RegisterUseCase
import auth.domain.usecase.ResetPasswordUseCase
import auth.domain.usecase.SendPasswordResetUseCase


/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object DiContainer {
    fun createLoginUseCase(): LoginUseCase = LoginUseCase(
        repository = DataModuleFactory.createLoginRepository()
    )
    fun createRegisterUseCase(): RegisterUseCase = RegisterUseCase(
        repository = DataModuleFactory.createRegisterRepository()
    )
    fun createAccountVerifyUseCase():AccountVerifyUseCase=AccountVerifyUseCase(
        repository = DataModuleFactory.createRegisterRepository()
    )
    fun createSendPasswordResetUseCase(): SendPasswordResetUseCase =
        SendPasswordResetUseCase(repository = DataModuleFactory.createLoginRepository())
    fun createResetPasswordUseCase(): ResetPasswordUseCase =ResetPasswordUseCase(
        repository = DataModuleFactory.createLoginRepository()
    )

}