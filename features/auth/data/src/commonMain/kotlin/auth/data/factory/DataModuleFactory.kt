package auth.data.factory

import auth.data.repository.LoginRepositoryImpl
import auth.data.repository.RegisterRepositoryImpl
import auth.domain.repository.LoginRepository
import auth.domain.repository.RegisterRepository
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
    fun createRegisterRepository(): RegisterRepository = RegisterRepositoryImpl(
        apiServiceClient = NetworkFactory.createAPIServiceClient(),
        jsonParser = NetworkFactory.createJsonParser()
    )
}