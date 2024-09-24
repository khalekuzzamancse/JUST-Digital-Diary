package auth.data.factory

import auth.data.repository.LoginRepositoryImpl
import auth.domain.repository.LoginRepository
import factory.NetworkFactory
/**
 * - Prevent consumer module to create direct instance to reduce coupling instead create
 * instance via `factory method`
 */
object DataModuleFactory {
    fun createLoginRepository(): LoginRepository =LoginRepositoryImpl(
        apiServiceClient = NetworkFactory.createAPIServiceClient(),
        jsonParser = NetworkFactory.createJsonParser()
    )
}