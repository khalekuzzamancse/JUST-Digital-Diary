@file:Suppress("unused")
package core.database.api

import domain.api.CalenderApi

/**
 * - Just to hide the `domain` module component to the consumer module
 * of this module, because `Domain.AcademicApi` is not accessible from the consumer module of this module
 * - Need not a abstract version of of it because it like a use case means it all dependencies are abstract and it has
 * no implementation, so it behaves same as abstract though it is concrete class
 */
class CalenderApiFacade internal  constructor(
    private val contractApi: CalenderApi,
) {
    /**@param calender Json format of academic calender*/
    suspend fun insert(calender: String) = contractApi.insert(json = calender)
    suspend fun readOfCurrentYear() = contractApi.readOfCurrentYear()
    suspend fun  readAll()=contractApi.readAll()
    /**@param calender Json format of academic calender*/
    suspend fun update(year: Int, calender: String) = contractApi.update(year, calender)
    suspend fun delete(year: Int) = contractApi.deleteCalender(year)
}