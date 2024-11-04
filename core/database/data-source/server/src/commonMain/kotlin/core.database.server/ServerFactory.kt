package core.database.server

import domain.api.AdministrationApi
import domain.factory.ContractFactory
import factory.NetworkFactory

object ServerFactory {
    fun serverApi(token: String): ServerAcademicApiDeprecated =
        ServerAcademicApiDeprecated(
            token = token,
            apiServiceClient = NetworkFactory.createAPIServiceClient(),
            parser = NetworkFactory.createJsonParser()
        )

    fun serverApi2(token: String): ServerAcademicApi2 =
        ServerAcademicApi2(
            token = token,
            apiServiceClient = NetworkFactory.createAPIServiceClient(),
            parser = NetworkFactory.createJsonParser(),
            feedbackService = ContractFactory.feedbackService()
        )


    fun administrationApi(token: String): AdministrationApi = ServerAdministrationApi(
        token = token,
        apiService = NetworkFactory.createAPIServiceClient(),
        parser = NetworkFactory.createJsonParser(),
        feedbackService = ContractFactory.feedbackService()
    )
}