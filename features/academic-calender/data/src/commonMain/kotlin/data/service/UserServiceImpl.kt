package data.service

import domain.exception.CustomException
import domain.model.User
import domain.service.UserService

class UserServiceImpl: UserService {
    override fun validateAuthenticity(user: User): CustomException? {
        TODO("Not yet implemented")
    }
}