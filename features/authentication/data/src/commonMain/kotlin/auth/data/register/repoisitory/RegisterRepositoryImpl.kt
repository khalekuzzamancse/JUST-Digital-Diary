package auth.data.register.repoisitory

import auth.data.PackageLevelAccess
import auth.data.register.remote.RemoteDataSource
import auth.data.register.remote.entity.RegisterRequestEntity
import auth.domain.register.model.RegisterRequestModel
import auth.domain.register.repository.RegisterRepository

@OptIn(PackageLevelAccess::class) //Okay to use RegisterRequestEntity,RemoteDataSource() register package
class RegisterRepositoryImpl : RegisterRepository {
    override suspend fun register(model: RegisterRequestModel): Result<String> {
        return RemoteDataSource().register(
            body = RegisterRequestEntity(
                username = model.username,
                password = model.password,
                email = model.email,
                name = model.name
            )
        )
    }

}