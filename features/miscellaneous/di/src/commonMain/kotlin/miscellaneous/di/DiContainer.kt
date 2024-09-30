@file:Suppress("unused")
package miscellaneous.di

import miscellaneous.data.factory.DataFactory
import miscellaneous.domain.usecase.GetEventsUseCase
import miscellaneous.domain.usecase.GetVCInfoUseCase

object DiContainer {
    fun createGetVCInfoUseCase(token: String?) =
        GetVCInfoUseCase(DataFactory.createRepository(token))

    fun createGetEventsUseCase(token: String?) =
        GetEventsUseCase(DataFactory.createRepository(token))
}