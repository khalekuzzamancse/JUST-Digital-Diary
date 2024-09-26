package auth.data.dto

import auth.data.entity.LoginEntity
import auth.data.entity.RegisterRequestEntity
import auth.domain.model.LoginModel
import auth.domain.model.RegisterModel

internal object RegisterModelDTO {
    fun toEntity(model: RegisterModel): RegisterRequestEntity {
        return RegisterRequestEntity(
            name = model.name,
            email = model.username,
            password = model.password,
            username = model.username,

            )
    }
}