package data

import data.repository.RepositoryImpl
import factory.NetworkFactory
import faculty.domain.repository.Repository

object DataFactory {
    fun createFacultyListRepository(): Repository =
        RepositoryImpl(
            apiServiceClient = NetworkFactory.createAPIServiceClient(),
            jsonParser = NetworkFactory.createJsonParser()
        )


}