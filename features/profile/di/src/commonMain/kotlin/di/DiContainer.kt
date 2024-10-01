@file:Suppress("unused")
package di

import data.factory.DataFactory
import domain.usecase.GetProfileUseCase

object DiContainer {
    fun createGetVCInfoUseCase(token: String) =
        GetProfileUseCase(DataFactory.createRepository(token))
}