@file:Suppress("UnUsed")

package data.repository

import component.ApiServiceClient
import component.JsonParser
import data.misc.CalendarBuilder
import domain.exception.CalendarFeatureException
import domain.model.AcademicCalendar
import domain.model.CalendarModel
import domain.model.DayNameModel
import domain.repository.CalenderRepository

class CalenderRepositoryImpl(
    val apiServiceClient: ApiServiceClient,
    val jsonParser: JsonParser
) : CalenderRepository {
    override fun addCalender(calender: AcademicCalendar): CalendarFeatureException? {
        TODO("Not yet implemented")
    }

    override fun retrieveCalender(year: Int): Result<CalendarModel> {

        return Result.success(
            CalendarBuilder()
                .addWeekend(DayNameModel.THURSDAY)
                .addWeekend(DayNameModel.FRIDAY)
                .build(2024)
        )

//        val json = calenderJson
//        val result = jsonParser.parse(json, CalendarWrapperSchema.serializer())
//        return result.fold(
//            onSuccess = { calenderWrapper ->
//                with(SchemaToModelConverter) {
//                    val model=calenderWrapper.academicCalendar.toModel()
//                    return@with Result.success(model)
//                }
//
//            },
//            onFailure = {exception->
//                Result.failure(
//                    CalendarFeatureException.MiscException(
//                        message = "Failed to load calender",
//                        debugMessage = exception.cause?.message?:"Json parsing error at:${this.javaClass.name}"
//                    )
//                )
//            }
//        )
    }
}