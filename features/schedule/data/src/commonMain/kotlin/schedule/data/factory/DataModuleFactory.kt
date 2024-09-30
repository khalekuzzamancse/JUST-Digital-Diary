package schedule.data.factory

import schedule.data.repository.RepositoryImpl
import schedule.domain.repository.Repository
import factory.NetworkFactory

object DataModuleFactory {
    fun createRepository(): Repository = RepositoryImpl(
        NetworkFactory.createAPIServiceClient(),
        NetworkFactory.createJsonParser()
    )
}