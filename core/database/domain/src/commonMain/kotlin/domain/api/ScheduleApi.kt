@file:Suppress("unused")
package domain.api

import domain.entity.FeedbackMessageEntity
import domain.entity.schedule.*

interface ScheduleApi {
    /**
     * Return all schedules under the dept
     */
    suspend fun readByDept(deptId: String): String

    suspend fun insert(deptId:String,json: String): String
    /**
     * @param json JSON version of list of [ClassScheduleReadEntity]
     * @return JSON version of [FeedbackMessageEntity]
     */
    suspend fun update(id: String, json: String): String
    /**
     * @return JSON version of [FeedbackMessageEntity]
     */
    suspend fun delete(id:String): String
    /**Used by admin to delete  old once,read all schedule for all dept*/
    suspend fun readAll(): String
}

