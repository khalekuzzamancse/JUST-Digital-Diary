@file:Suppress("unused")
package core.database.api

import domain.api.ScheduleApi

/**
 * - Just to hide the `domain` module component to the consumer module
 * of this module, because `Domain...Api` is not accessible from the consumer module of this module
 * - Need not a abstract version of of it because it like a use case means it all dependencies are abstract and it has
 * no implementation, so it behaves same as abstract though it is concrete class
 */
class ScheduleApiFacade internal  constructor(
    private val contractApi: ScheduleApi,
) {
    /**@param json Json format of  schedule*/
    suspend fun insert(deptId:String, json: String) = contractApi.insert(deptId =deptId , json = json)
    suspend fun readUnderDept(deptId: String) = contractApi.readByDept(deptId)
    suspend fun  readAll()=contractApi.readAll()
    /**@param json Json format of schedule*/
    suspend fun update(id: String, json: String) = contractApi.update(id = id, json = json)
    suspend fun delete(id: String) = contractApi.delete(id)
}