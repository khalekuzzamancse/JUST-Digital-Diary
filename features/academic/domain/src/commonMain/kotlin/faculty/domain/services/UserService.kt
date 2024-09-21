@file:Suppress("UnUsed")

package faculty.domain.services

import common.docs.domain_layer.CustomExceptionDoc
import common.docs.domain_layer.ServiceDoc
import faculty.domain.exception.CustomException


/**
 *  Further discussion on:
 *  - `Service`: see [ServiceDoc]
 *  - return `Custom Exception` instead of `throwing` : see [CustomExceptionDoc]
 */
interface UserService {
    /**
     * - Validates user has right permission or not for adding/updating or fetching the calender to database
     */
    fun validateAuthenticity(userId:String): CustomException?
}
