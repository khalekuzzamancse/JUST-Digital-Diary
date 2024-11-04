package core.database.server

import domain.api.AdministrationApi
import domain.factory.ContractFactory
import factory.NetworkFactory

object ServerFactory {
     fun serverApi(token: String): ServerAcademicApi =
        ServerAcademicApi(
            token = token,
            apiServiceClient = NetworkFactory.createAPIServiceClient(),
            parser = NetworkFactory.createJsonParser()
        )
    fun administrationApi(token: String):AdministrationApi=ServerAdministrationApi(
        token = token,
        apiService = NetworkFactory.createAPIServiceClient(),
        parser = NetworkFactory.createJsonParser(),
        feedbackService = ContractFactory.feedbackService()
    )
}