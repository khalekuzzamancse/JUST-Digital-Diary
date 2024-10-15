package data.service

import feature.academiccalender.domain.exception.CustomException
import feature.academiccalender.domain.model.User
import feature.academiccalender.domain.service.UserService

class UserServiceImpl: UserService {
    override fun validateAuthenticity(user: User): CustomException? {
        TODO("Not yet implemented")
    }
}