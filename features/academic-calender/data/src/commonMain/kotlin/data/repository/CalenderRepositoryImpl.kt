@file:Suppress("UnUsed", "FunctionName")

package data.repository

import core.network.ApiServiceClient
import core.network.JsonParser
import data.data_source.holidayJson
import data.misc.CalendarBuilder
import data.misc.SchemaToModelConverter
import data.entity.CalendarWrapperSchema
import domain.exception.CustomException
import domain.model.AcademicCalendar
import domain.model.CalendarModel
import domain.model.DayOfWeek
import domain.model.Holiday
import domain.model.HolidayModel
import domain.repository.CalenderRepository
import java.time.Month

class CalenderRepositoryImpl(
    val apiServiceClient: ApiServiceClient,
    val jsonParser: JsonParser
) : CalenderRepository {
    override fun addCalender(calender: AcademicCalendar): CustomException? {
        println("Calender")
        TODO("Not yet implemented")
    }

    override suspend fun retrieveAcademicCalender(year: Int): Result<CalendarModel> {
        val yearCalender = CalendarBuilder()
            .addWeekend(DayOfWeek.THURSDAY)
            .addWeekend(DayOfWeek.FRIDAY)
            .build(year)

        val json = holidayJson
        val result = jsonParser.parse(json, CalendarWrapperSchema.serializer())

        return result.fold(
            onSuccess = { calenderWrapper ->
                with(SchemaToModelConverter) {
                    val academicCalendar = calenderWrapper.academicCalendar.toModel()
                    return@with Result.success(
                        _addHolidaysToCalendarModel(
                            academicCalendar,
                            yearCalender
                        )
                    )
                }

            },
            onFailure = { exception ->
                Result.failure(
                    CustomException.MiscException(
                        message = "Failed to load calender",
                        debugMessage = exception.cause?.message
                            ?: "Json parsing error at:${this.javaClass.name}"
                    )
                )
            }
        )

        //  return Result.success(calendar)


    }

    override suspend fun retrieveRawCalender(
        year: Int, weekend: List<DayOfWeek>
    ): Result<CalendarModel> {
        var builder = CalendarBuilder()
        weekend.forEach {
            builder = builder.addWeekend(it)
        }
        return Result.success(builder.build(year))
    }
}

// Function to merge holidays from AcademicCalendar into CalendarModel
private fun _addHolidaysToCalendarModel(
    academicCalendar: AcademicCalendar,
    calendarModel: CalendarModel
): CalendarModel {
    val updatedMonths = calendarModel.months.map { monthModel ->
        val holidays = _getHolidaysForMonth(academicCalendar, monthModel.month)
        val updatedDays = monthModel.days.map { dayModel ->
            val holiday = holidays.find { it.day == dayModel.date }
            if (holiday != null) {
                // Update the DayModel to include the holiday
                dayModel.copy(
                    holiday = HolidayModel(
                        type = holiday.holidayType,
                        reason = holiday.reason
                    )
                )
            } else {
                dayModel
            }
        }
        monthModel.copy(days = updatedDays)
    }

    return calendarModel.copy(months = updatedMonths)
}

// Helper function to retrieve the holidays for a given month from AcademicCalendar
private fun _getHolidaysForMonth(academicCalendar: AcademicCalendar, month: Month): List<Holiday> {
    return when (month) {
        Month.JANUARY -> academicCalendar.januaryHolidays
        Month.FEBRUARY -> academicCalendar.februaryHolidays
        Month.MARCH -> academicCalendar.marchHolidays
        Month.APRIL -> academicCalendar.aprilHolidays
        Month.MAY -> academicCalendar.mayHolidays
        Month.JUNE -> academicCalendar.juneHolidays
        Month.JULY -> academicCalendar.julyHolidays
        Month.AUGUST -> academicCalendar.augustHolidays
        Month.SEPTEMBER -> academicCalendar.septemberHolidays
        Month.OCTOBER -> academicCalendar.octoberHolidays
        Month.NOVEMBER -> academicCalendar.novemberHolidays
        Month.DECEMBER -> academicCalendar.decemberHolidays
    }
}