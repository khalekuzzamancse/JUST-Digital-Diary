package auth.data.dto

import auth.data.entity.LoginEntity
import auth.domain.model.LoginModel

object LoginModelDTO {
    fun loginEntityToModel(schema: LoginEntity): LoginModel {
        return LoginModel(
            username = schema.email,
            password = schema.password
        )
    }
    fun loginModelToEntity(model: LoginModel): LoginEntity {
        return LoginEntity(
            email = model.username,
            password = model.password
        )
    }
}