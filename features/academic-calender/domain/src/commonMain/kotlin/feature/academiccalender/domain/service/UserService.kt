@file:Suppress("UnUsed")

package feature.academiccalender.domain.service

import common.docs.domain_layer.CustomExceptionDoc
import common.docs.domain_layer.ServiceDoc
import feature.academiccalender.domain.exception.CustomException
import feature.academiccalender.domain.model.User

/**
 *  Further discussion on:
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
interface UserService {
    /**
     * - Validates user has right permission or not for adding/updating or fetching the calender to database
     */
    fun validateAuthenticity(user: User): CustomException?
}
