@file:Suppress("unused")

package domain.factory

import domain.service.AcademicInsertionService
import domain.service.AcademicReadEntityService
import domain.service.CalenderInsertionService
import domain.service.CalenderReadEntityService
import domain.service.FeedbackMessageService
import domain.service.JsonParser
import domain.service.ScheduleService

object ContractFactory {
    fun jsonParser(): JsonParser = JsonParserImpl()
    fun feedbackService(): FeedbackMessageService = FeedbackMessageServiceImpl()
    fun insertionService(): AcademicInsertionService = InsertionServiceImpl()
    fun calenderInsertionService():CalenderInsertionService=CalenderInsertionServiceImpl()
    fun academicReadEntityService(): AcademicReadEntityService = AcademicReadEntityServiceImpl()
    fun calenderReadEntityService():CalenderReadEntityService=CalenderReadEntityServiceImpl()
    fun scheduleService(): ScheduleService=ScheduleServiceImpl()


}