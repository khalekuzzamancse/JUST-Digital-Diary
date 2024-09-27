package auth.data.dto

import auth.data.entity.AccountVerifyEntity
import auth.data.entity.RegisterRequestEntity
import auth.domain.model.AccountVerifyModel
import auth.domain.model.RegisterModel

internal object RegisterModelDTO {
    fun toEntity(model: RegisterModel): RegisterRequestEntity {
        return RegisterRequestEntity(
            name = model.name,
            email = model.email,
            password = model.password,
            username = model.username,

            )
    }
    fun toEntity(model: AccountVerifyModel)=AccountVerifyEntity(
        identifier =model.username,
        code = model.otp
    )
}