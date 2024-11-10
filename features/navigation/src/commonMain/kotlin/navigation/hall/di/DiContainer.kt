package navigation.hall.di

import navigation.hall.data.LoginRepositoryImpl
import navigation.hall.data.RegisterRepositoryImpl
import navigation.hall.domain.repository.LoginRepository
import navigation.hall.domain.repository.RegisterRepository
import navigation.hall.domain.usecase.AccountVerifyUseCase
import navigation.hall.domain.usecase.LoginUseCase
import navigation.hall.domain.usecase.RegisterUseCase
import navigation.hall.domain.usecase.ResetPasswordCodeValidateUseCase
import navigation.hall.domain.usecase.ResetPasswordUseCase
import navigation.hall.domain.usecase.SendPasswordResetUseCase


/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object DiContainer {
    fun createLoginUseCase(): LoginUseCase = LoginUseCase(
        repository = loginRepository()
    )
    fun createRegisterUseCase(): RegisterUseCase = RegisterUseCase(
        repository = registerRepository()
    )
    fun createAccountVerifyUseCase(): AccountVerifyUseCase =AccountVerifyUseCase(
        repository = registerRepository()
    )
    fun createSendPasswordResetUseCase(): SendPasswordResetUseCase =
        SendPasswordResetUseCase(repository = loginRepository())
    fun createResetPasswordUseCase(): ResetPasswordUseCase =ResetPasswordUseCase(
        repository = loginRepository()
    )
    fun codeValidateCase()= ResetPasswordCodeValidateUseCase(
        repository = loginRepository()
    )
    private fun loginRepository():LoginRepository=LoginRepositoryImpl()
    private fun registerRepository():RegisterRepository=RegisterRepositoryImpl()

}