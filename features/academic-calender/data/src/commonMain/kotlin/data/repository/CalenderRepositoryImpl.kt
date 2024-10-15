@file:Suppress("FunctionName")

package data.repository

import core.database.api.ApiFactory
import core.network.JsonParser
import data.entity.AcademicCalenderEntity
import data.misc.CalendarBuilder
import data.misc.HodidayAdder
import data.misc.JsonHandler
import data.misc.ModelMapper
import data.misc.withExceptionHandle
import feature.academiccalender.domain.model.AcademicCalender2
import feature.academiccalender.domain.model.CalendarModel
import feature.academiccalender.domain.model.DayOfWeek
import feature.academiccalender.domain.repository.CalenderRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.Year

class CalenderRepositoryImpl internal constructor(
    private val handler: JsonHandler,
    val jsonParser: JsonParser,
) : CalenderRepository {
    private val api = ApiFactory.calenderApi()

    override suspend fun insert(calender: AcademicCalender2): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(ModelMapper) { calender.toEntity() }
                val calenderJson = Json.encodeToString(entity)
                val responseJson = api.insert(calenderJson)
                return Result.failure(responseJson.parseAsServerMessageOrThrow())//Sending feedback message as failure,though it is success
            }
        }
    }

    override suspend fun update(calender: AcademicCalender2): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(ModelMapper) { calender.toEntity() }
                val calenderJson = Json.encodeToString(entity)
                val responseJson = api.update(entity.year, calenderJson)
                return Result.failure(responseJson.parseAsServerMessageOrThrow())
            }
        }
    }

    override suspend fun readAcademicCalender(): Result<CalendarModel> {
        return with(handler) {
            withExceptionHandle {
                val json = api.readOfCurrentYear()
                if (json._isHolidayEntity()) {
                    val entity = jsonParser.parseOrThrow(json, AcademicCalenderEntity.serializer())
                    val rawCalender = CalendarBuilder()
                        .addWeekend(DayOfWeek.THURSDAY)
                        .addWeekend(DayOfWeek.FRIDAY)
                        .build(entity.year)
                    val academicCalender = HodidayAdder().add(rawCalender, entity)
                    Result.success(academicCalender)
                } else
                    Result.failure(parseAsServerMessageOrThrowCustomException(json))
            }
        }

    }

    override suspend fun readRawCalender(): Result<CalendarModel> {
        val year = Year.now().value
        val weekend: List<DayOfWeek> = listOf(DayOfWeek.FRIDAY, DayOfWeek.THURSDAY)
        var builder = CalendarBuilder()
        weekend.forEach {
            builder = builder.addWeekend(it)
        }
        return Result.success(builder.build(year))
    }

    private fun String._isHolidayEntity() =
        jsonParser.parse(this, AcademicCalenderEntity.serializer()).isSuccess
}