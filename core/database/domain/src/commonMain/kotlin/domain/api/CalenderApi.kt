@file:Suppress("unused")

package domain.api

import domain.entity.calender.AcademicCalenderEntity
import domain.entity.FeedbackMessageEntity

/**
 * - Define set of apis for `AcademicCalendar` or the `HolidayCalendar`
 */
interface CalenderApi {
    /**
     * @return on success return JSON version of list of [AcademicCalenderEntity] of current year calendar(if available)
     * on failure return the JSON version of [FeedbackMessageEntity].
     */
    suspend fun readOfCurrentYear(): String
    /**
     * @param json JSON version of list of [AcademicCalenderEntity]
     * @return JSON version of [FeedbackMessageEntity]
     */
    suspend fun insert(json: String): String
    /**
     * @param json JSON version of list of [AcademicCalenderEntity]
     * @return JSON version of [FeedbackMessageEntity]
     */
    suspend fun update(year: Int, json: String): String

    /**
     * @return JSON version of [FeedbackMessageEntity]
     */
    suspend fun deleteCalender(year:Int): String
    /**Used by admin to delete  old once*/
    suspend fun readAll(): String
}