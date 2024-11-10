package navigation.hall.domain.repository

import navigation.hall.domain.model.AccountVerifyModel
import navigation.hall.domain.model.RegisterModel


interface RegisterRepository {
    /**@return message from server or CustomException*/
    suspend fun register(model: RegisterModel): Result<String>

    /**@return message from server or CustomException*/
    suspend fun verifyAccount(model: AccountVerifyModel): Result<String>

}

