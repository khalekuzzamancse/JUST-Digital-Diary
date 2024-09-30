package data.service

import domain.exception.CalendarFeatureException
import domain.model.User
import domain.service.UserService

class UserServiceImpl: UserService {
    override fun validateAuthenticity(user: User): CalendarFeatureException? {
        TODO("Not yet implemented")
    }
}